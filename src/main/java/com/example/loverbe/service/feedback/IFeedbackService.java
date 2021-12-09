package com.example.loverbe.service.feedback;

import com.example.loverbe.model.entity.Feedback;
import com.example.loverbe.service.IGeneralService;

import java.util.Optional;

public interface IFeedbackService extends IGeneralService<Feedback> {

    Optional<Feedback> findByReservationId(Long id);

}
