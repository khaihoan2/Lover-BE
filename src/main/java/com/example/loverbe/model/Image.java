package com.example.loverbe.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User user;

    public Image() {

    }

    public Image(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
