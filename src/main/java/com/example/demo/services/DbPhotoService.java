package com.example.demo.services;

import com.example.demo.models.Photo;
import com.example.demo.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service("mainPhotoService")
public class DbPhotoService implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public Iterable<Photo> getAll() {
        return photoRepository.findAll();
    }

    public Optional<Photo> getById(long id) {
        return photoRepository.findById(id);
    }

    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Optional<Photo> updatePhoto(long id, Photo photo) {

        Optional<Photo> foundPhoto = photoRepository.findById(id);

        if(foundPhoto.isEmpty()) {
            Optional.empty();
        }

        foundPhoto.get().setUrl(photo.getUrl());

        return foundPhoto;
    }

    public Boolean deletePhoto(long id) {

        Optional<Photo> foundPhoto = photoRepository.findById(id);

        if(foundPhoto.isEmpty()) {
            return false;
        }

        photoRepository.delete(foundPhoto.get());

        return true;
    }
}
