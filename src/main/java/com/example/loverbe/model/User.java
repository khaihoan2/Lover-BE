package com.example.loverbe.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

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

    private double height;

    private double weight;

    private double bust;

    private double waist;

    private double hips;

    private String description;

    private String facebookUrl;

    private Date joinedAt;

    private Date lastLoginAt;

    private String username;

    private String password;

    private int viewCounter;

    private int rentedCounter;

    private String status;
}
