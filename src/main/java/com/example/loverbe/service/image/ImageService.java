package com.example.loverbe.service.image;

import com.example.loverbe.model.entity.Image;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService{

    @Autowired
    private IImageRepository imageRepository;

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Iterable<Image> findAllByUserId(Long id) {
        return imageRepository.findAllByUserId(id);
    }

    @Override
    public List<Image> findAllByUser(User user) {
        return imageRepository.findAllByUser(user);
    }
}
