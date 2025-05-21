package com.example.demo.controllers.apis;

import com.example.demo.models.Photo;
import com.example.demo.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class PhotoController {

    @Autowired
    @Qualifier("mainPhotoService")
    private PhotoService photoService;

    public PhotoController() {}

    @GetMapping("/api/photos")
    public Iterable<Photo> getAll() {
        return photoService.getAll();
    }

    @GetMapping("/api/photos/{id}")
    public Photo getById(@PathVariable long id) {

        Optional<Photo> photo = photoService.getById(id);

        if(photo.isEmpty()) {
            throw new RuntimeException("photo not found");
        }
        return photo.get();
    }
}
