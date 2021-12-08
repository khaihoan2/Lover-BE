package com.example.loverbe.model.dto;

import com.example.loverbe.model.City;
import com.example.loverbe.model.Nationality;
import com.example.loverbe.model.Role;
import com.example.loverbe.model.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Set;

@Data
public class UserForm {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Long yearOfBirth;

    private String gender;

    private City city;

    private Nationality nationality;

    private Double height;

    private Double weight;

    private Double bust;

    private Double waist;

    private Double hips;

    private String description;

    private String facebookUrl;

    private Date joinedAt;

    private Date lastLoginAt;

    private String username;

    private String password;

    private Set<Role> roles;

    private Integer viewCounter;

    private Integer rentedCounter;

    private String status;

    private MultipartFile avatar;

    private Set<MultipartFile> images;

    public static User extract(UserForm userForm) {
        User user = new User();
        user.setId(userForm.getId());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        user.setPhone(userForm.getPhone());
        user.setYearOfBirth(userForm.getYearOfBirth());
        user.setGender(userForm.getGender());
        user.setCity(userForm.getCity());
        user.setNationality(userForm.getNationality());
        user.setWeight(userForm.getWeight());
        user.setHeight(userForm.getHeight());
        user.setBust(userForm.getBust());
        user.setWaist(userForm.getWaist());
        user.setHips(userForm.getHips());
        user.setDescription(userForm.getDescription());
        user.setFacebookUrl(userForm.getFacebookUrl());
        user.setJoinedAt(userForm.getJoinedAt());
        user.setLastLoginAt(userForm.getLastLoginAt());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setRoles(userForm.getRoles());
//        user.setViewCounter(userForm.getViewCounter());
        user.setRentedCounter(userForm.getRentedCounter());
        user.setStatus(userForm.getStatus());
        return user;
    }
}
