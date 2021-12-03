package com.example.loverbe.controller;

import com.example.loverbe.model.Feedback;
import com.example.loverbe.service.feedback.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/feedbacks")
public class FeedbackRestController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<Iterable<Feedback>> getAll() {
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Feedback> findOneFeedback(@PathVariable(name = "id") Long id) {
        Optional<Feedback> feedbackOptional = feedbackService.findById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(feedbackOptional.get());
    }

    @PostMapping
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
        return new ResponseEntity<>(feedbackService.save(feedback), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Feedback> editFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Optional<Feedback> feedbackOptional = feedbackService.findById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (feedback.getId() != null) {
                feedback.setId(id);
            }
            return new ResponseEntity<>(feedbackService.save(feedback), HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable Long id) {
        Optional<Feedback> feedbackOptional = feedbackService.findById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            feedbackService.delete(id);
            return new ResponseEntity<>(feedbackOptional.get(), HttpStatus.OK);
        }
    }
}
