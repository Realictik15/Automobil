package com.automobil.backend.rest;

import com.automobil.backend.models.Clients;
import com.automobil.backend.models.Messages;
import com.automobil.backend.models.Reviews;
import com.automobil.backend.repository.AdvertisRepository;
import com.automobil.backend.repository.ClassesRepository;
import com.automobil.backend.repository.ClientsRepository;
import com.automobil.backend.repository.CountriesRepository;
import com.automobil.backend.repository.EnginesRepository;
import com.automobil.backend.repository.GenerationsRepository;
import com.automobil.backend.repository.MarksRepository;
import com.automobil.backend.repository.MessagesRepository;
import com.automobil.backend.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class Test {
    private final CountriesRepository countriesRepository;
    private final ClassesRepository classesRepository;
    private final MarksRepository marksRepository;
    private final AdvertisRepository advertisRepository;
    private final EnginesRepository enginesRepository;
    private final GenerationsRepository generationsRepository;
    private final ClientsRepository clientsRepository;
    private final ReviewsRepository reviewsRepository;
    private final MessagesRepository messagesRepository;

    @Autowired
    public Test(CountriesRepository countriesRepository, ClassesRepository classesRepository, MarksRepository marksRepository, AdvertisRepository advertisRepository, EnginesRepository enginesRepository, GenerationsRepository generationsRepository, ClientsRepository clientsRepository, ReviewsRepository reviewsRepository, MessagesRepository messagesRepository) {
        this.countriesRepository = countriesRepository;
        this.classesRepository = classesRepository;
        this.marksRepository = marksRepository;
        this.advertisRepository = advertisRepository;
        this.enginesRepository = enginesRepository;
        this.generationsRepository = generationsRepository;
        this.clientsRepository = clientsRepository;
        this.reviewsRepository = reviewsRepository;
        this.messagesRepository = messagesRepository;
    }



}
