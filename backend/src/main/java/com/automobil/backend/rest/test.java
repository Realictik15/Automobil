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
public class test {
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
    public test(CountriesRepository countriesRepository, ClassesRepository classesRepository, MarksRepository marksRepository, AdvertisRepository advertisRepository, EnginesRepository enginesRepository, GenerationsRepository generationsRepository, ClientsRepository clientsRepository, ReviewsRepository reviewsRepository, MessagesRepository messagesRepository) {
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

    @GetMapping("/a")
    public String get() throws ParseException {
//        Optional<Countries> countries = countriesRepository.findById((long) 1);
//        Optional<Generations> generations = generationsRepository.findById((long) 1);
       // Optional<Clients> clients = clientsRepository.findById((long) 1);

        Clients c = new Clients();
      //
        c.setFirstName("dsfsfsf");
        c.setLastName("fsf");
        c.setEmale("dsfasgfas");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-05-12");
        c.setBornDay(date);
        c.setLogin("dsfs");
        c.setPass("DFDFD");
        c.setTelephone((long) 1111111111);
        c.setMessages(new ArrayList<Messages>(){{add(messagesRepository.findById((long)2).get());}});
        clientsRepository.save(c);
        c.getMessages().forEach(x-> System.out.println(x.getIdMess()));


        System.out.println(messagesRepository.findById((long)2).get().getClients()+"id clint"+messagesRepository.findById((long)2).get().getReviews()+"id revi");

//      //  r.setClients(clientsRepository.findById((long) 5).get());
//        System.out.println(messagesRepository.findById((long) 1).get().getClients().getIdUser() +" "+messagesRepository.findById((long) 1).get().getReviews().getIdRevi());
//        c.getReviews().forEach(x -> System.out.println(x.getIdRevi()));

        return "/";
    }

    @GetMapping("/b")
    public String getb() {
        System.out.println(marksRepository.findAll());

        return "/";
    }

}
