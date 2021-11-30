package com.example.loverbe.service.user;

import com.example.loverbe.model.User;
import com.example.loverbe.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User findByUsername(String username);

}
