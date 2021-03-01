package com.automobil.backend.service;

import com.automobil.backend.models.Advertisments;

import java.util.List;

public interface AdvertService {

    List<Advertisments> listAll();

    Advertisments getById(Long id);

    Advertisments save(Advertisments advertisments);

    void update(Advertisments advertisments);

    void delete(Advertisments advertisments);

    void deleteById(Long id);

}

