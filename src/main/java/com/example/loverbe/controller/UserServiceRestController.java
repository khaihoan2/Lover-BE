package com.example.loverbe.controller;

import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.entity.UserService;
import com.example.loverbe.service.user_service.IUserServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user-services")
public class UserServiceRestController {

    @Autowired
    private IUserServiceService userServiceService;

    @GetMapping
    public ResponseEntity<Iterable<UserService>> findAll() {
        return new ResponseEntity<>(userServiceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserService> findById(@PathVariable Long id) {
        Optional<UserService> userServiceOptional = userServiceService.findById(id);
        if (!userServiceOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userServiceOptional.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/buyer/{id}")
    public ResponseEntity<Iterable<UserService>> findByUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        Iterable<UserService> userServices = userServiceService.findByUser(user);
        return new ResponseEntity<>(userServices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserService> addNew(@RequestBody UserService userService) {
        return new ResponseEntity<>(userServiceService.save(userService), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserService> update(@RequestBody UserService userService,
                                              @PathVariable Long id) {
        Optional<UserService> userServiceOptional = userServiceService.findById(id);
        if (!userServiceOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userServiceService.save(userService), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserService> deleteById(@PathVariable Long id) {
        Optional<UserService> userServiceOptional = userServiceService.findById(id);
        if (!userServiceOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userServiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
