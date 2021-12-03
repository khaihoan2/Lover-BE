package com.example.loverbe.service.image;

import com.example.loverbe.model.Image;
import com.example.loverbe.model.User;
import com.example.loverbe.service.IGeneralService;

import java.util.List;

public interface IImageService extends IGeneralService<Image> {
    List<Image> findAllByUser(User user);
}
