package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.models.Advertisments;
import com.automobil.backend.service.AdvertService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertServiceImpl implements AdvertService {
    @Override
    public List<Advertisments> listAll() {
        return null;
    }

    @Override
    public Advertisments getById(Long id) {
        return null;
    }

    @Override
    public Advertisments save(Advertisments advertisments) {
        return null;
    }

    @Override
    public void update(Advertisments advertisments) {

    }

    @Override
    public void delete(Advertisments advertisments) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
