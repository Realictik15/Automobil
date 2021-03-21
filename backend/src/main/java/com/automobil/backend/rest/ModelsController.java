package com.automobil.backend.rest;

import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Models;
import com.automobil.backend.service.ModelsService;
import com.automobil.backend.transfer.AdvertReviewDetails;
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
@RequestMapping("/models")
public class ModelsController {
    private final ModelsService modelsService;

    @Autowired
    public ModelsController(ModelsService modelsService) {
        this.modelsService = modelsService;
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> getAllModels() {
        List<ModelDto> modelDtos = modelsService.listAll();
        if (modelDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelDtos, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelDto> getModelByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(modelsService.getById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelDto> getModelByTitle(@PathVariable("title") String title) throws EntityNotFoundException {
        if (title == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Models model = modelsService.getModelByTitle(title);
        return new ResponseEntity<>(modelsService.toModelDTO(model), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}/generetions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenerationsDto>> getAllGenerationFromModel(@PathVariable("id") Long id) throws EntityNotFoundException {
        List<GenerationsDto> generationsDtos = modelsService.getListGen(id);
        if (generationsDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(generationsDtos, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addModel(@Validated(New.class) @RequestBody ModelDto modelDto) throws EntityNotFoundException {
        if (modelDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            modelsService.save(modelDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteModel(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        modelsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
