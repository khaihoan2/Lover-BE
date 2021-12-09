package com.example.loverbe.controller;

import com.example.loverbe.model.entity.Service;
import com.example.loverbe.service.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
public class ServiceRestController {
    @Autowired
    private IServiceService serviceService;

    @GetMapping
    public ResponseEntity<Iterable<Service>> findAll() {
        return new ResponseEntity<>(serviceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> findById(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        if (!serviceOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(serviceOptional.get(),HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Service> addNew(@RequestBody Service service) {
        return new ResponseEntity<>(serviceService.save(service), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> update(@RequestBody Service service,
                                          @PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        if (!serviceOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(serviceService.save(service), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Service> deleteById(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        if (!serviceOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        serviceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
