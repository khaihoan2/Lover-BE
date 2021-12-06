package com.example.loverbe.service.reservationDetail;

import com.example.loverbe.model.entity.ReservationDetail;
import com.example.loverbe.repository.IReservationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationDetailService implements IReservationDetailService {

    @Autowired
    private IReservationDetailRepository reservationDetailRepository;

    @Override
    public Iterable<ReservationDetail> findAll() {
        return reservationDetailRepository.findAll();
    }

    @Override
    public ReservationDetail save(ReservationDetail reservationDetail) {
        return reservationDetailRepository.save(reservationDetail);
    }

    @Override
    public void delete(Long id) {
        reservationDetailRepository.deleteById(id);
    }

    @Override
    public Optional<ReservationDetail> findById(Long id) {
        return reservationDetailRepository.findById(id);
    }
}
