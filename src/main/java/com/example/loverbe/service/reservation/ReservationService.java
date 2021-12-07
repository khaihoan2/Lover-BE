package com.example.loverbe.service.reservation;

import com.example.loverbe.model.Reservation;
import com.example.loverbe.model.User;
import com.example.loverbe.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Override
    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }
}
