package com.example.loverbe.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User renter;

    private User rentee;

    private Date meetingDate;

    private Date startFrom;

    private Date endAt;

    private String location;

    private Date reserveAt;

    private Boolean status;

    public Reservation() {
    }

    public Reservation(Long id, User renter, User rentee, Date meetingDate, Date startFrom, Date endAt, String location, Date reserveAt, Boolean status) {
        this.id = id;
        this.renter = renter;
        this.rentee = rentee;
        this.meetingDate = meetingDate;
        this.startFrom = startFrom;
        this.endAt = endAt;
        this.location = location;
        this.reserveAt = reserveAt;
        this.status = status;
    }
}
