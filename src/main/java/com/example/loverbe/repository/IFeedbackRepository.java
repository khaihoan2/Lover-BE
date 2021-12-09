package com.example.loverbe.repository;

import com.example.loverbe.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {

    Optional<Feedback> findByReservationId(Long id);
}
