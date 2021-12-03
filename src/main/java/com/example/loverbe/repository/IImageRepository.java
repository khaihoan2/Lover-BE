package com.example.loverbe.repository;

import com.example.loverbe.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select * from image where user_id = ?1", nativeQuery = true)
    Iterable<Image> findAllByUserId(Long id);
}
