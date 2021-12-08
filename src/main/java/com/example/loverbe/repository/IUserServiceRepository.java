package com.example.loverbe.repository;

import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.entity.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserServiceRepository extends JpaRepository<UserService, Long> {

    Iterable<UserService> findByUser(User user);
}
