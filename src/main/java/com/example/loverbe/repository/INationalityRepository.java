package com.example.loverbe.repository;

import com.example.loverbe.model.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INationalityRepository extends JpaRepository<Nationality, Long> {
}