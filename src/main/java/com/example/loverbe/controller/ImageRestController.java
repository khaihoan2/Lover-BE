package com.example.loverbe.controller;


import com.example.loverbe.model.dto.ImageForm;
import com.example.loverbe.model.entity.Image;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.service.image.IImageService;
import com.example.loverbe.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImageRestController {

    @Autowired
    private IImageService imageService;

    @Autowired
    private UserService userService;

    @Value("${file-upload}")
    private String fileUpload;


    @GetMapping
    public ResponseEntity<Iterable<Image>> getAllImage() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> findImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(imageOptional.get(), HttpStatus.OK);
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Iterable<Image>> findImagesByUserId(@PathVariable Long id) {
        Iterable<Image> images = imageService.findAllByUserId(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@Validated @RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.CREATED);
    }

    @PostMapping("many")
    public ResponseEntity<?> saveImages(ImageForm imageForm) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<MultipartFile> images = imageForm.getName();
        if (images != null) {
            for (MultipartFile multipartFile : images) {
                Image image = new Image();
                image.setUser(user);
                String imageName = multipartFile.getOriginalFilename();
                image.setName(imageName);
                imageService.save(image);
                try {
                    FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + imageName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> editImage(@PathVariable Long id, @RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (image.getId() == null) {
                image.setId(id);
            }
            return new ResponseEntity<>(imageService.save(image), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        if (!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            imageService.delete(id);
            return new ResponseEntity<>(imageOptional.get(), HttpStatus.OK);
        }
    }
}
