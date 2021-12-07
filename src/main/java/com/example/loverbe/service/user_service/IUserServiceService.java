package com.example.loverbe.service.user_service;

import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.entity.UserService;
import com.example.loverbe.service.IGeneralService;

public interface IUserServiceService extends IGeneralService<UserService> {

    Iterable<UserService> findByUser(User user);

}
