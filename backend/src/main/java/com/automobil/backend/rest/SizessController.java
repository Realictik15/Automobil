package com.automobil.backend.rest;

import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.SizessService;
import com.automobil.backend.transfer.AdminDetails;
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
@RequestMapping("/sizess")
public class SizessController {
    private final SizessService sizessService;

    public SizessController(SizessService sizessService) {
        this.sizessService = sizessService;
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SizessDto> getSizess(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sizessService.getById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSizess(@Validated(New.class) @RequestBody SizessDto sizessDto) throws EntityNotFoundException {
        if (sizessDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            sizessService.save(sizessDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteSizess(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sizessService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "/generations/{idgen}/carbody/{idcarbody}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SizessDto> getSizessByTwoId(@PathVariable("idgen") Long idgen,
                                                      @PathVariable("idcarbody") Long idcarbody) throws EntityNotFoundException {
        if (idgen == null || idcarbody==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sizessService.getSize(idgen,idcarbody), HttpStatus.OK);
    }

}
