package com.example.loverbe.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Service service;

    private Double price;
}
