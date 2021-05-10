package com.automobil.backend.rest;

import com.automobil.backend.api.ApiService;
import com.automobil.backend.api.ReportApi;
import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.FiltersDto;
import com.automobil.backend.dto.FormAdvert;
import com.automobil.backend.dto.ReportDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.AdvertService;
import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/advert")
public class AdvertismentController {
    private static final Logger LOGGER = LogManager.getLogger(AdvertismentController.class.getName());
    private final AdvertService advertService;
    private final ApiService apiService;

    @Autowired
    public AdvertismentController(AdvertService advertService, ApiService apiService) {
        this.advertService = advertService;
        this.apiService = apiService;
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity<?> saveAdvertisment(@ModelAttribute FormAdvert formAdvert) throws EntityNotFoundException, ParseException, IOException {
        if (formAdvert == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            List<String> list = advertService.saveUploadedFiles(formAdvert.getFiles());
            advertService.save(formAdvert, list);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(AdminDetails.class)
    @GetMapping(value = "admin/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdvertismentDto>> getAllAdvert() {
        List<AdvertismentDto> advertismentDtos = advertService.getlistAll();
        if (advertismentDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(advertismentDtos, HttpStatus.OK);

    }

    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "class/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdvertismentDto>> getAllByClass(@PathVariable("id") Long id) {
        List<AdvertismentDto> advertismentDtos = advertService.getListByClass(id);
        if (advertismentDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(advertismentDtos, HttpStatus.OK);

    }

    @JsonView(AdvertReviewDetails.class)
    @GetMapping("/all")
    public ResponseEntity<Page<AdvertismentDto>> listPosts(@RequestParam(defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        Page<AdvertismentDto> adverPage = advertService.getListAllAvaliblePage(page, size);
        if (adverPage.getTotalElements() < 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adverPage, HttpStatus.OK);

    }

    @JsonView(AdvertReviewDetails.class)
    @PostMapping(value = "/all/filters", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<AdvertismentDto> listPostsFilters(@RequestParam(defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size,
                                                  @RequestBody FiltersDto filtersDto) {
        return advertService.getListFilters(filtersDto, page, size);

    }


    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdvertismentDto>> getAllAvailAdvert() {
        List<AdvertismentDto> advertismentDtos = advertService.getlistAvalible();
        if (advertismentDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(advertismentDtos, HttpStatus.OK);

    }


    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvertismentDto> getAdvertismentById(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(advertService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}/report/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDto> getReport(@PathVariable("id") Long id, @PathVariable("vin") String vin) throws EntityNotFoundException {
        if (id == null && vin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReportDto reportDto = apiService.getGibddInfo(vin);
        reportDto.setAdvertismentDto(advertService.getReport(id));
        LOGGER.info("received report id:{}, vin:{}",id,vin);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PatchMapping(value = "{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchAdvert(@PathVariable("id") Long id, @Validated(Existing.class) @RequestBody AdvertismentDto advertismentDto) throws EntityNotFoundException, DataIntegrityViolationException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (advertismentDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        advertService.update(advertismentDto);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAdvert(@PathVariable("id") Long id) throws EntityNotFoundException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        advertService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping(value = "{id}/userdelete")
    public ResponseEntity<?> userDeleteAdvert(@PathVariable("id") Long id) throws EntityNotFoundException, ParseException, IOException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        advertService.userDeleteById(id);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

