package com.example.loverbe.controller;

import com.example.loverbe.model.Reservation;
import com.example.loverbe.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/reservations")
@RestController
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
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation) {
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
