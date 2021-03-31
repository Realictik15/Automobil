package com.automobil.backend.rest;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.EnginesService;
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

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/engines")
@CrossOrigin//(origins = " http://localhost:4200")
public class EnginesComtroller {
    private final EnginesService enginesService;

    @Autowired
    public EnginesComtroller(EnginesService enginesService) {
        this.enginesService = enginesService;
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnginesDto>> getAllEngines() {
        List<EnginesDto> enginesDtos = enginesService.listAll();
        if (enginesDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enginesDtos, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnginesDto> getEngineByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(enginesService.getById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @ExceptionHandler({ConstraintViolationException.class})
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEngine(@Validated(New.class) @RequestBody EnginesDto enginesDto) throws EntityNotFoundException {
        if (enginesDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            enginesService.save(enginesDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
