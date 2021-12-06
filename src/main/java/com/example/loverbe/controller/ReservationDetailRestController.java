package com.example.loverbe.controller;

import com.example.loverbe.model.entity.ReservationDetail;
import com.example.loverbe.service.reservationDetail.IReservationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservation-details")
@CrossOrigin("*")
public class ReservationDetailRestController {

    @Autowired
    private IReservationDetailService reservationDetailService;

    @GetMapping
    public ResponseEntity<Iterable<ReservationDetail>> findAll() {
        Iterable<ReservationDetail> reservationDetails = reservationDetailService.findAll();
        return new ResponseEntity<>(reservationDetails, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationDetail> findById(@PathVariable Long id) {
        Optional<ReservationDetail> reservationDetail = reservationDetailService.findById(id);
        if (reservationDetail.isPresent()) {
            return new ResponseEntity<>(reservationDetail.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ReservationDetail> save(@RequestBody ReservationDetail reservationDetail) {
        ReservationDetail reservationDetailSave = reservationDetailService.save(reservationDetail);
        return new ResponseEntity<>(reservationDetailSave, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservationDetail> edit(@PathVariable Long id, @RequestBody ReservationDetail reservationDetail) {
        Optional<ReservationDetail> reservationDetailOptional = reservationDetailService.findById(id);
        if (reservationDetailOptional.isPresent()) {
            reservationDetail.setId(id);
            return new ResponseEntity<>(reservationDetailService.save(reservationDetail), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
