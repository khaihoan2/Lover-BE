package com.example.loverbe.repository;

import com.example.loverbe.model.entity.Reservation;
import com.example.loverbe.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    Iterable<Reservation> findByRenter(User user);
}
