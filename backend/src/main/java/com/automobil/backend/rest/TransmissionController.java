package com.automobil.backend.rest;

import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.TransmissionService;
import com.automobil.backend.transfer.Details;
import com.automobil.backend.transfer.New;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transmission/")
public class TransmissionController {
    private final TransmissionService transmissionService;

    @Autowired
    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    @JsonView(Details.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransmissionsDto> getTransmission(@PathVariable("id") Long id) throws EntityNotFoundException {

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transmissionService.getById(id), HttpStatus.OK);

    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addGer(@Validated(New.class) @RequestBody TransmissionsDto transmissionsDto) throws EntityNotFoundException {
        if (transmissionsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            transmissionService.save(transmissionsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTransmission(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        transmissionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @JsonView(Details.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransmissionsDto>> getAllMarks() {
        List<TransmissionsDto> transmissionsDtos = transmissionService.listAll();
        if (transmissionsDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transmissionsDtos, HttpStatus.OK);
    }
}