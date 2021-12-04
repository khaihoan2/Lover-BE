package com.example.loverbe.service.user_service;

import com.example.loverbe.model.entity.UserService;
import com.example.loverbe.repository.IUserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceService implements IUserServiceService {

    @Autowired
    private IUserServiceRepository userServiceRepository;

    @Override
    public Iterable<UserService> findAll() {
        return userServiceRepository.findAll();
    }

    @Override
    public UserService save(UserService userService) {
        return userServiceRepository.save(userService);
    }

    @Override
    public void delete(Long id) {
        userServiceRepository.deleteById(id);
    }

    @Override
    public Optional<UserService> findById(Long id) {
        return userServiceRepository.findById(id);
    }
}
