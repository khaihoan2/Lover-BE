package com.example.loverbe.model.dto;

import com.example.loverbe.model.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageForm {
    private Long id;

    private List<MultipartFile> name;

    private User user;

    public ImageForm() {
    }

    public ImageForm(Long id, List<MultipartFile> name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}
