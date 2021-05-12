package com.automobil.backend.rest;

import com.automobil.backend.dto.*;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.ModificationsService;
import com.automobil.backend.transfer.Details;
import com.automobil.backend.transfer.New;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modification")
public class ModificationController {
    @Autowired
    private ModificationsService modificationsService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}/compl", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompleteSetsDto>> getAllComplSetsModification(@PathVariable("id") Long id) throws EntityNotFoundException {
        List<CompleteSetsDto> completeSetsDtoList = modificationsService.getListCompl(id);
        if (completeSetsDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(completeSetsDtoList, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addModif(@Validated(New.class) @RequestBody ModificationsDto modificationsDto) throws EntityNotFoundException {
        if (modificationsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            modificationsService.save(modificationsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModificationsDto>> getAllModification() {
        List<ModificationsDto> modificationsDtoList = modificationsService.listAll();
        if (modificationsDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modificationsDtoList, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModificationsDto> getModificationByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modificationsService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}/engin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnginesDto> getEnginByModif(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modificationsService.getEngin(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}/transmission", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransmissionsDto> getTransmissionByModif(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modificationsService.getTransmission(id), HttpStatus.OK);
    }
}
