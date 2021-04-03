package com.automobil.backend.rest;

import com.automobil.backend.dto.CarbodyDto;
import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Marks;
import com.automobil.backend.service.CarBodyService;
import com.automobil.backend.service.MarksService;
import com.automobil.backend.transfer.AdminDetails;
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
@RequestMapping("/carbody")
//@CrossOrigin//(origins = " http://localhost:4200")
public class CarBodyController {
    private final CarBodyService carBodyService;

    @Autowired
    public CarBodyController(CarBodyService carBodyService) {
        this.carBodyService = carBodyService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(AdminDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarbodyDto>> getAllCarBody() {
        List<CarbodyDto> carbodyDtos = carBodyService.getListCarBody();
        if (carbodyDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carbodyDtos, HttpStatus.OK);
    }
    @JsonView(AdminDetails.class)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarbodyDto> getCarBodyByID(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carBodyService.getCarBody(id), HttpStatus.OK);
    }
    @PreAuthorize(" hasAuthority('ADMIN')")
    @JsonView(AdminDetails.class)
    @GetMapping(value = "title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarbodyDto> getCarBodyByTitle(@PathVariable("title") String title) throws EntityNotFoundException {
        if (title == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carBodyService.getCarBodyByTitle(title), HttpStatus.OK);
    }


}
