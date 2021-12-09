package com.example.loverbe.service.feedback;

import com.example.loverbe.model.entity.Feedback;
import com.example.loverbe.repository.IFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
@Service
public class FeedbackService implements IFeedbackService{

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Override
    public Iterable<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback save(Feedback feedback) {
        Date date = new Date(System.currentTimeMillis());
        feedback.setSentAt(date);
        return feedbackRepository.save(feedback);
    }

    @Override
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Optional<Feedback> findByReservationId(Long id) {
        return feedbackRepository.findByReservationId(id);
    }
}
