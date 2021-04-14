package com.automobil.backend.rest;

import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.GearBoxesService;
import com.automobil.backend.transfer.AdvertReviewDetails;
import com.automobil.backend.transfer.Details;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gear")
public class GeraBoxesController {
    @Autowired
    private GearBoxesService gearBoxesService;

    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GearBoxesDto>> getAllMarks() {
        List<GearBoxesDto> gearBoxes = gearBoxesService.getListGearBox();
        if (gearBoxes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gearBoxes, HttpStatus.OK);

    }

    @JsonView(Details.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GearBoxesDto> getMarkByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gearBoxesService.getById(id), HttpStatus.OK);
    }
}
