package com.example.loverbe.service.nationality;

import com.example.loverbe.model.entity.Nationality;
import com.example.loverbe.repository.INationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NationalityService implements INationalityService{

    @Autowired
    private INationalityRepository nationalityRepository;

    @Override
    public Iterable<Nationality> findAll() {
        return nationalityRepository.findAll();
    }

    @Override
    public Nationality save(Nationality nationality) {
        return nationalityRepository.save(nationality);
    }

    @Override
    public void delete(Long id) {
        nationalityRepository.deleteById(id);
    }

    @Override
    public Optional<Nationality> findById(Long id) {
        return nationalityRepository.findById(id);
    }
}
