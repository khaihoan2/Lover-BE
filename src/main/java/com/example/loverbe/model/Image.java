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

}
