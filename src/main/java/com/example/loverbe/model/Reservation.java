package com.example.loverbe.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User renter;

    @ManyToOne
    private User rentee;

    private Timestamp startFrom;

    private Timestamp endAt;

    private String location;

    private Timestamp reserveAt;

    private Boolean status;

    public Reservation() {
    }

    public Reservation(Long id, User renter, User rentee, Timestamp startFrom, Timestamp endAt, String location, Timestamp reserveAt, Boolean status) {
        this.id = id;
        this.renter = renter;
        this.rentee = rentee;
        this.startFrom = startFrom;
        this.endAt = endAt;
        this.location = location;
        this.reserveAt = reserveAt;
        this.status = status;
    }
}
