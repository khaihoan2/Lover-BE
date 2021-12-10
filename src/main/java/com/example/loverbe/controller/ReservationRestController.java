package com.example.loverbe.controller;

import com.example.loverbe.model.dto.ReservationForm;
import com.example.loverbe.model.entity.Reservation;
import com.example.loverbe.model.entity.ReservationDetail;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.string_constant.ReservationStatus;
import com.example.loverbe.service.reservation.IReservationService;
import com.example.loverbe.service.reservationDetail.IReservationDetailService;
import com.example.loverbe.service.user.IUserService;
import com.example.loverbe.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin("*")
public class ReservationRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IReservationDetailService reservationDetailService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Reservation>> findAll(@RequestParam(required = false) Long sellerId) {
        if (sellerId == null) {
            return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
        } else {
            Optional<User> userOptional = userService.findById(sellerId);
            if (!userOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(reservationService.findByRentee(userOptional.get()), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        if (reservation.isPresent()) {
            return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cart")
    public ResponseEntity<Iterable<Reservation>> findByRenter() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Iterable<Reservation> reservations = reservationService.findByRenter(user);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReservationForm reservationForm) {
        Date date = new Date(System.currentTimeMillis());
        Reservation reservation = new Reservation();
        reservation.setRenter(reservationForm.getRenter());
        reservation.setRentee(reservationForm.getRentee());
        reservation.setLocation(reservationForm.getLocation());
        reservation.setTotalMoney(reservationForm.getTotalMoney());
        int startFromHour = Integer.parseInt(reservationForm.getStartFrom().substring(0, 2));
        int startFromMinute = Integer.parseInt(reservationForm.getStartFrom().substring(3));
        Timestamp startForm = new Timestamp(date.getYear(), date.getMonth(), date.getDay(), startFromHour, startFromMinute, 0, 0);
        reservation.setStartFrom(startForm);
        int endAtHour = Integer.parseInt(reservationForm.getEndAt().substring(0, 2));
        int endAtMinute = Integer.parseInt(reservationForm.getEndAt().substring(3));
        Timestamp endAt = new Timestamp(date.getYear(), date.getMonth(), date.getDay(), endAtHour, endAtMinute, 0, 0);
        reservation.setEndAt(endAt);
        reservation.setReserveAt(new Timestamp(System.currentTimeMillis()));
        reservation.setStatus(ReservationStatus.PENDING);
        Reservation reservationSave = reservationService.save(reservation);

        List<ReservationDetail> reservationDetails = reservationForm.getReservationDetails();
        for (ReservationDetail reservationDetail : reservationDetails) {
            reservationDetail.setReservation(reservationSave);
            reservationDetailService.save(reservationDetail);
        }
        return new ResponseEntity<>(reservationSave, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> edit(@PathVariable Long id, @RequestBody Reservation reservation) {
        Optional<Reservation> optionalReservation = reservationService.findById(id);
        if (optionalReservation.isPresent()) {
            reservation.setId(id);
            reservation.setRenter(optionalReservation.get().getRenter());
            reservation.setRentee(optionalReservation.get().getRentee());
            reservation.setStartFrom(optionalReservation.get().getStartFrom());
            reservation.setEndAt(optionalReservation.get().getEndAt());
            reservation.setTotalMoney(optionalReservation.get().getTotalMoney());
            reservation.setLocation(optionalReservation.get().getLocation());
            reservation.setReserveAt(optionalReservation.get().getReserveAt());
            return new ResponseEntity<>(reservationService.save(reservation), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        if (reservation.isPresent()) {
            reservationService.delete(id);
            return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
