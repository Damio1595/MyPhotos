package com.example.demo.services;

import com.example.demo.models.Photo;
import java.util.Optional;

public interface PhotoService {

    public Iterable<Photo> getAll();

    public Optional<Photo> getById(long id);

    public Photo createPhoto(Photo photo);

    public Optional<Photo> updatePhoto(long id, Photo photo);

    public Boolean deletePhoto(long id);
}
