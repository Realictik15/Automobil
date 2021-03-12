package com.automobil.backend.rest;

import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.ModificationsService;
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
@RequestMapping("/modification/")
public class ModificationController {
    @Autowired
    private ModificationsService modificationsService;

    @JsonView(Details.class)
    @GetMapping(value = "{id}/compl", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompleteSetsDto>> getAllModelsFormMark(@PathVariable("id") Long id) throws EntityNotFoundException {
        List<CompleteSetsDto> completeSetsDtoList = modificationsService.getListCompl(id);
        if (completeSetsDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(completeSetsDtoList, HttpStatus.OK);

    }
}
