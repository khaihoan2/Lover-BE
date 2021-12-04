package com.example.loverbe.controller;


import com.example.loverbe.model.entity.Image;
import com.example.loverbe.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImageRestController {
    @Autowired
    private IImageService imageService;


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
    public ResponseEntity<Iterable<Image>> findByUserId(@PathVariable Long id) {
        Iterable<Image> images = imageService.findAllByUserId(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@Validated @RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.CREATED);
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
