package com.example.loverbe.service.user;

import com.example.loverbe.model.User;
import com.example.loverbe.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User findByUsername(String username);

    Page<User> findByName(String userName, String firstName, String viewCounter, String status, Pageable pageable);

}
