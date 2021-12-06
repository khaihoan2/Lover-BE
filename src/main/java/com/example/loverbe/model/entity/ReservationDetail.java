package com.example.loverbe.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReservationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reservation reservation;

    private String serviceName;

    private Double price;

    public ReservationDetail() {
    }

    public ReservationDetail(Long id, Reservation reservation, String serviceName, Double price) {
        this.id = id;
        this.reservation = reservation;
        this.serviceName = serviceName;
        this.price = price;
    }
}
