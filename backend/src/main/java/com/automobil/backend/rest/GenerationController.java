package com.automobil.backend.rest;

import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.GenerationsService;
import com.automobil.backend.transfer.Details;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generation")
public class GenerationController {
    private final GenerationsService generationsService;

    public GenerationController(GenerationsService generationsService) {
        this.generationsService = generationsService;
    }

    @JsonView(Details.class)
    @GetMapping(value = "{id}/size", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SizessDto>> getAllModelsFormMark(@PathVariable("id") Long id) throws EntityNotFoundException {
        List<SizessDto> siz = generationsService.getAllSize(id);
        if (siz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(siz, HttpStatus.OK);

    }
    @JsonView(Details.class)
    @GetMapping(value = "{id}/modification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModificationsDto>> getModificationByGen(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(generationsService.getModificationByGen(id), HttpStatus.OK);
    }
}
