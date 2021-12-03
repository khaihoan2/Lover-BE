package com.example.loverbe.service.image;

import com.example.loverbe.model.Image;
import com.example.loverbe.service.IGeneralService;

public interface IImageService extends IGeneralService<Image> {

    Iterable<Image> findAllByUserId(Long id);

}
