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

    public UserService() {
    }

    public UserService(User user, Service service, Double price) {
        this.user = user;
        this.service = service;
        this.price = price;
    }
}
