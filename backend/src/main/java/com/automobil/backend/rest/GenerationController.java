package com.automobil.backend.rest;

import com.automobil.backend.dto.*;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.GenerationsService;
import com.automobil.backend.transfer.AdvertReviewDetails;
import com.automobil.backend.transfer.Details;
import com.automobil.backend.transfer.New;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generation")
public class GenerationController {
    private final GenerationsService generationsService;

    public GenerationController(GenerationsService generationsService) {
        this.generationsService = generationsService;
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenerationsDto>> getAllGeneration() {
        List<GenerationsDto> generationsDtos = generationsService.listAll();
        if (generationsDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(generationsDtos, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}/size", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SizessDto>> getAllSizesGeneration(@PathVariable("id") Long id) throws EntityNotFoundException {
        List<SizessDto> siz = generationsService.getAllSize(id);
        if (siz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(siz, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}/modification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModificationsDto>> getModificationByGen(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(generationsService.getModificationByGen(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addGeneration(@Validated(New.class) @RequestBody GenerationsDto generationsDto) throws EntityNotFoundException {
        if (generationsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            generationsService.save(generationsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenerationsDto> getGenerationByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(generationsService.getById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}/carbody", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarbodyDto>> getCarBodyByIDGeneration(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(generationsService.getListCarBodyByGenenerationId(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteGenaration(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        generationsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
