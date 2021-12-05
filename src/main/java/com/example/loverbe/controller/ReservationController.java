package com.example.loverbe.controller;

import com.example.loverbe.model.dto.ReservationForm;
import com.example.loverbe.model.entity.Reservation;
import com.example.loverbe.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Controller
@RequestMapping("/api/reservations")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @GetMapping
    public ResponseEntity<Iterable<Reservation>> findAll() {
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        if (reservation.isPresent()) {
            return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReservationForm reservationForm) {
        Date date = new Date(System.currentTimeMillis());
        Reservation reservation = new Reservation();
        reservation.setRenter(reservationForm.getRenter());
        reservation.setRentee(reservationForm.getRentee());
        reservation.setLocation(reservation.getLocation());
        int startFromHour = Integer.parseInt(reservationForm.getStartFrom().substring(0, 2));
        int startFromMinute = Integer.parseInt(reservationForm.getStartFrom().substring(3));
        Timestamp startForm = new Timestamp(date.getYear(), date.getMonth(), date.getDay(), startFromHour, startFromMinute, 0, 0);
        reservation.setStartFrom(startForm);
        int endAtHour = Integer.parseInt(reservationForm.getStartFrom().substring(0, 2));
        int endAtMinute = Integer.parseInt(reservationForm.getStartFrom().substring(3));
        Timestamp endAt = new Timestamp(date.getYear(), date.getMonth(), date.getDay(), endAtHour, endAtMinute, 0, 0);
        reservation.setEndAt(endAt);
        reservation.setReserveAt(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(reservationService.save(reservation), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Reservation> edit(@PathVariable Long id, @RequestBody Reservation reservation) {
        Optional<Reservation> optionalReservation = reservationService.findById(id);
        if (optionalReservation.isPresent()) {
            reservation.setId(id);
            return new ResponseEntity<>(reservationService.save(reservation), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reservation> delete(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        if (reservation.isPresent()) {
            reservationService.delete(id);
            return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
