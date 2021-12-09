package com.example.loverbe.controller;

import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.entity.UserService;
import com.example.loverbe.service.user.IUserService;
import com.example.loverbe.service.user_service.IUserServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user-services")
public class UserServiceRestController {

    @Autowired
    private IUserServiceService userServiceService;

    @Autowired
    private IUserService userService;

//    @GetMapping
//    public ResponseEntity<Iterable<UserService>> findAll() {
//        return new ResponseEntity<>(userServiceService.findAll(), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Iterable<UserService>> findBySeller(@RequestParam Long sellerId) {
        Optional<User> userOptional = userService.findById(sellerId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userServiceService.findByUser(userOptional.get()), HttpStatus.OK);
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
    public ResponseEntity<List<UserService>> addNew(@RequestBody List<UserService> userServices) {
        List<UserService> userServiceIterableResult = new ArrayList<>();
        for (UserService userService : userServices) {
            userServiceIterableResult.add(userServiceService.save(userService));
        }
        return new ResponseEntity<>(userServiceIterableResult, HttpStatus.CREATED);
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
