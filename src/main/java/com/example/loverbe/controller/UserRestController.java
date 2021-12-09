package com.example.loverbe.controller;

import com.example.loverbe.model.entity.Image;
import com.example.loverbe.model.IUserBuyerDetail;
import com.example.loverbe.model.entity.Service;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.dto.UserForm;
import com.example.loverbe.model.entity.UserService;
import com.example.loverbe.service.image.IImageService;
import com.example.loverbe.service.service.IServiceService;
import com.example.loverbe.service.user.IUserService;
import com.example.loverbe.service.user_service.IUserServiceService;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private IUserServiceService userServiceService;

    @Autowired
    private IImageService imageService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<IUserBuyerDetail>> search(@RequestParam(name = "username", required = false) String username,
                                             @RequestParam(name = "firstName", required = false) String firstName,
                                             @RequestParam(name = "viewCounterMin", required = false) Long viewCounterMin,
                                             @RequestParam(name = "viewCounterMax", required = false) Long viewCounterMax,
                                             Pageable pageable) {
        if (username == null) {
            username = "";
        }
        if (firstName == null) {
            firstName = "";
        }
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

    @GetMapping("checkUsername/{username}")
    public ResponseEntity<Object> findByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("gallery")
    public ResponseEntity<Iterable<IUserBuyerDetail>> findUserByJoinedAtDesc(@RequestParam(required = false) Long page) {
        Iterable<IUserBuyerDetail> users = userService.findUserByJoinedAtDesc(page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("rating")
    public ResponseEntity<Iterable<IUserBuyerDetail>> findUserHighestRanking() {
        Iterable<IUserBuyerDetail> userBuyerDetails = userService.findUserHighestRanking();
        return new ResponseEntity<>(userBuyerDetails, HttpStatus.OK);
    }

    @GetMapping("ratingLimitFemaleLimitMale")
    public ResponseEntity<Iterable<IUserBuyerDetail>> findUserLimitFemaleLimitMale() {
        Iterable<IUserBuyerDetail> userBuyerDetails = userService.findUserLimitFemaleLimitMale();
        return new ResponseEntity<>(userBuyerDetails, HttpStatus.OK);
    }

    @GetMapping("gallery/totalElement")
    public ResponseEntity<Long> getTotalElementDescJoinedAt() {
        Long totalElement = userService.getTotalEntityDescJoinedAt();
        return new ResponseEntity<>(totalElement, HttpStatus.OK);
    }

    @GetMapping("suitableProposal")
    public ResponseEntity<Iterable<IUserBuyerDetail>> getUserSuitable() {
        Iterable<IUserBuyerDetail> userBuyerDetails = userService.findUserSuitable();
        return new ResponseEntity<>(userBuyerDetails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addNew(@RequestBody UserForm userForm) {

        User user = UserForm.extract(userForm);
        user.setViewCounter(0L);
        user.setRentedCounter(0);
        user.setJoinedAt(Date.valueOf(LocalDate.now()));
        user.setLastLoginAt(Date.valueOf(LocalDate.now()));

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

    @PutMapping("user/{id}")
    public ResponseEntity<User> editUser(UserForm userForm, @PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        user.setPhone(userForm.getPhone());
        user.setDescription(userForm.getDescription());
        MultipartFile avatar = userForm.getAvatar();
        if (avatar != null) {
            String avatarFileName = avatar.getOriginalFilename();
            user.setAvatar(avatarFileName);
            try {
                FileCopyUtils.copy(avatar.getBytes(), new File(fileUpload + avatarFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        user.setId(id);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
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
        if (avatar != null) {
            String avatarFileName = avatar.getOriginalFilename();
            user.setAvatar(avatarFileName);
            try {
                FileCopyUtils.copy(avatar.getBytes(), new File(fileUpload + avatarFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // save other images into database and static folder
        Set<MultipartFile> images = userForm.getImages();
        if (images != null) {
            for (MultipartFile image : images) {
                String imageFileName = image.getOriginalFilename();
                try {
                    FileCopyUtils.copy(image.getBytes(), new File(fileUpload + imageFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageService.save(new Image(imageFileName, user));
            }
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
            try {
                Files.delete(Paths.get(fileUpload + image.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageService.delete(image.getId());
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
