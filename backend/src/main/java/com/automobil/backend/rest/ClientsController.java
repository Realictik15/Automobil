package com.automobil.backend.rest;

import com.automobil.backend.dto.*;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.ModelsMapper;
import com.automobil.backend.mapStruct.TransmissionMapper;
import com.automobil.backend.models.Models;
import com.automobil.backend.models.Transmissions;
import com.automobil.backend.service.AdvertService;
import com.automobil.backend.service.EnginesService;
import com.automobil.backend.service.ModificationsService;
import com.automobil.backend.service.TransmissionService;
import com.automobil.backend.transfer.AdvertReviewDetails;
import com.automobil.backend.transfer.Details;
import com.automobil.backend.transfer.Existing;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/")
public class ClientsController {
    private final EnginesService enginesService;
    private final TransmissionService transmissionService;
    private final ModificationsService modificationsService;
    private final AdvertService advertService;
    @Autowired
    private ModelsMapper modelsMapper;

    @Autowired
    public ClientsController(EnginesService enginesService, TransmissionService transmissionService, ModificationsService modificationsService, AdvertService advertService) {
        this.enginesService = enginesService;
        this.transmissionService = transmissionService;
        this.modificationsService = modificationsService;
        this.advertService = advertService;
    }

    @JsonView(Details.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnginesDto>> getAllEn() {
        List<EnginesDto> engines = this.enginesService.listAll();
        if (engines.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(engines, HttpStatus.OK);
    }


    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}")
    public ResponseEntity<AdvertismentDto> getModif(@PathVariable("id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(advertService.getById(id), HttpStatus.OK);
    }

    @JsonView(AdvertReviewDetails.class)
    @PostMapping(value = "a", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public void getTest(@Validated(Existing.class) @RequestBody ModelDto modelDto) {
        System.out.println(modelDto);
        Models models =modelsMapper.toModels(modelDto);
        System.out.println(models.getMark().getTitle()+" "+models.getMark().getImage());

    }
}
