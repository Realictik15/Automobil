package com.automobil.backend.rest;

import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.CompleteSetsService;
import com.automobil.backend.transfer.AdminDetails;
import com.automobil.backend.transfer.AdvertReviewDetails;
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
@RequestMapping("/compls")
public class CompleteSetsController {
    private final CompleteSetsService completeSetsService;

    public CompleteSetsController(CompleteSetsService completeSetsService) {
        this.completeSetsService = completeSetsService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(AdminDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompleteSetsDto>> getAllClients() {
        List<CompleteSetsDto> completeSetsDtos = this.completeSetsService.getAll();
        if (completeSetsDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(completeSetsDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompleteSetsDto> getComplsById(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(completeSetsService.getById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCompls(@Validated(New.class) @RequestBody CompleteSetsDto completeSetsDto) throws EntityNotFoundException {
        if (completeSetsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            completeSetsService.save(completeSetsDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCompl(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        completeSetsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
