package com.example.demo.services;

import com.example.demo.models.Photo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private List<Photo> listaPhoto;

    private long lastId;

    public PhotoServiceImpl() {

        listaPhoto = new ArrayList<>();
        listaPhoto.add(new Photo(1, "./img/01.png"));
        listaPhoto.add(new Photo(2, "./img/02.png"));
        listaPhoto.add(new Photo(3, "./img/03.png"));
        listaPhoto.add(new Photo(4, "./img/04.png"));
        listaPhoto.add(new Photo(5, "./img/05.png"));

        lastId = 5;

    }

    public Iterable<Photo> getAll() {
        return listaPhoto;
    }

    public Optional<Photo> getById(long id) {

        Optional<Photo> photo = listaPhoto.stream().filter(itemPhoto->itemPhoto.getId() == id).findFirst();

        return photo;
    }

    public Photo createPhoto(Photo photo) {

        lastId ++;

        photo.setId(lastId);

        listaPhoto.add(photo);

        return photo;
    }

    public Optional<Photo> updatePhoto(long id, Photo photo) {

        Optional<Photo> photoFound = listaPhoto.stream().filter(itemPhoto->itemPhoto.getId() == id).findFirst();

        if(photoFound.isEmpty()) {
            throw new RuntimeException("photo not found");
        }

        photoFound.get().setUrl(photo.getUrl());

        return photoFound;
    }

    public Boolean deletePhoto(long id) {

        Optional<Photo> deletedPhoto = listaPhoto.stream().filter(itemPhoto->itemPhoto.getId() == id).findFirst();

        if(deletedPhoto.isEmpty()) {

            return false;
        }

        listaPhoto.remove(deletedPhoto.get());

        return true;
    }
}
