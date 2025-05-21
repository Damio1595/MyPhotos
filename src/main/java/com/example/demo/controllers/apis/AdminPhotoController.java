package com.example.demo.controllers.apis;

import com.example.demo.models.Photo;
import com.example.demo.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AdminPhotoController {

    @Autowired
    @Qualifier("mainPhotoService")
    private PhotoService photoService;

    public AdminPhotoController() {}

    @GetMapping("admin/api/photos")
    public Iterable<Photo> getAll() {
        return photoService.getAll();
    }

    @GetMapping("admin/api/photos/{id}")
    public Photo getById(@PathVariable long id) {

        Optional<Photo> photo = photoService.getById(id);

        if(photo.isEmpty()) {
            throw new RuntimeException("photo not found");
        }
        return photo.get();
    }

    @PostMapping("admin/api/photos")
    public Photo createPhoto(@RequestBody Photo photo) {

        return photoService.createPhoto(photo);
    }

    @PutMapping("admin/api/photos/{id}")
    public Photo updatePhoto(@PathVariable long id, @RequestBody Photo photo) {

        Optional<Photo> photoFound = photoService.updatePhoto(id, photo);

        if(photoFound.isEmpty()) {
            throw new RuntimeException("photo not found");
        }

        photoFound.get().setUrl(photo.getUrl());

        return photoFound.get();
    }

    @DeleteMapping("admin/api/photos/{id}")
    public void deletePhoto(@PathVariable long id) {

        Boolean isDeleted = photoService.deletePhoto(id);

        if(isDeleted == false) {

            throw new RuntimeException("photo not found");
        }

    }
}
