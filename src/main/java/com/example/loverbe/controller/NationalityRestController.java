package com.example.loverbe.controller;

import com.example.loverbe.model.Nationality;
import com.example.loverbe.service.nationality.INationalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/api/nationalities")
@CrossOrigin("*")
public class NationalityRestController {

    @Autowired
    private INationalityService nationalityService;

    @GetMapping
    public ResponseEntity<Iterable<Nationality>> findAll() {
        return new ResponseEntity<>(nationalityService.findAll(), HttpStatus.OK);
    }
}
