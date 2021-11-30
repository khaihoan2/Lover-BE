package com.example.loverbe.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Image {
    private Long id;
    private String name;
    private User user;
}
