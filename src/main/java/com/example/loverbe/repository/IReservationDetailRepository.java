package com.example.loverbe.repository;

import com.example.loverbe.model.entity.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {
}
