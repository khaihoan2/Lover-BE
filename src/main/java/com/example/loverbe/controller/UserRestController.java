package com.example.loverbe.controller;

import com.example.loverbe.model.Image;
import com.example.loverbe.model.Role;
import com.example.loverbe.model.User;
import com.example.loverbe.model.dto.UserForm;
import com.example.loverbe.model.string_constant.RoleName;
import com.example.loverbe.service.image.IImageService;
import com.example.loverbe.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IImageService imageService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> search(@RequestParam(name = "username", required = false) String username,
                                             @RequestParam(name = "firstName", required = false) String firstName,
                                             @RequestParam(name = "viewCounterMin", required = false) Long viewCounterMin,
                                             @RequestParam(name = "viewCounterMax", required = false) Long viewCounterMax,
                                             Pageable pageable) {
        username=username.replaceAll(" ", "");
        firstName=firstName.replaceAll(" ", "");
        return ResponseEntity.ok(userService.findByNameFull(username, firstName, viewCounterMin, viewCounterMax, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addNew(@RequestBody UserForm userForm) {

        User user = UserForm.extract(userForm);

        // save the avatar into database and static folder
        MultipartFile avatar = userForm.getAvatar();
        String avatarFileName = avatar.getOriginalFilename();
        user.setAvatar(avatarFileName);
        try {
            FileCopyUtils.copy(avatar.getBytes(), new File(fileUpload + avatarFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // save other images into database and static folder
        Set<MultipartFile> images = userForm.getImages();
        for (MultipartFile image : images) {
            String imageFileName = image.getOriginalFilename();
            try {
                FileCopyUtils.copy(image.getBytes(), new File(fileUpload + imageFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageService.save(new Image(imageFileName, user));
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody UserForm userForm,
                                       @PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = UserForm.extract(userForm);

        // save the avatar into database and static folder
        MultipartFile avatar = userForm.getAvatar();
        String avatarFileName = avatar.getOriginalFilename();
        user.setAvatar(avatarFileName);
        try {
            FileCopyUtils.copy(avatar.getBytes(), new File(fileUpload + avatarFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // save other images into database and static folder
        Set<MultipartFile> images = userForm.getImages();
        for (MultipartFile image : images) {
            String imageFileName = image.getOriginalFilename();
            try {
                FileCopyUtils.copy(image.getBytes(), new File(fileUpload + imageFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageService.save(new Image(imageFileName, user));
        }

        user.setId(id);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //Delete all the images in DB and static folder
        List<Image> images = imageService.findAllByUser(userOptional.get());
        for (Image image : images) {
            new File(fileUpload + image.getName()).delete();
            imageService.delete(image.getId());
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
