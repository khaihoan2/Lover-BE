package com.example.loverbe.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String avatar;

    private String email;

    private String phone;

    private Long yearOfBirth;

    private String gender;

    @ManyToOne
    private City city;

    @ManyToOne
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

    private Integer viewCounter;

    private Integer rentedCounter;

    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;


}
