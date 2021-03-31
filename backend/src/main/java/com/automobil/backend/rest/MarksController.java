package com.automobil.backend.rest;

import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Marks;
import com.automobil.backend.service.MarksService;
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
@RequestMapping("/marks")
@CrossOrigin//(origins = " http://localhost:4200")
public class MarksController {
    @Autowired
    private MarksService marksService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MarksDto>> getAllMarks() {
        List<MarksDto> marksDtos = marksService.listAll();
        if (marksDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(marksDtos, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(Details.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarksDto> getMarkByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(marksService.getById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarksDto> getMarkByTitle(@PathVariable("title") String title) throws EntityNotFoundException {
        if (title == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Marks marks = marksService.getMarkByTitle(title);
        return new ResponseEntity<>(marksService.toMarksDTO(marks), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}/models", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModelDto>> getAllModelsFormMark(@PathVariable("id") Long id) throws EntityNotFoundException {
        List<ModelDto> modelDtos = marksService.getListModels(id);
        if (modelDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelDtos, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMark(@Validated(New.class) @RequestBody MarksDto marksDto) throws EntityNotFoundException {
        if (marksDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            marksService.save(marksDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMark(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        marksService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
