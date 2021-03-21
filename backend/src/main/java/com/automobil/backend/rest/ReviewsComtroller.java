package com.automobil.backend.rest;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.FormAdvert;
import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.dto.ReviewsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.service.ReviewsService;
import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/review")
public class ReviewsComtroller {
    private final ReviewsService reviewsService;

    public ReviewsComtroller(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity<?> saveReview(@Validated(New.class)@RequestBody ReviewsDto reviewsDto) throws EntityNotFoundException, ParseException, IOException {
        if (reviewsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {

            reviewsService.save(reviewsDto);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReviewsDto>> getAllReviews() {
        List<ReviewsDto> reviewsDtos = reviewsService.getAll();
        if (reviewsDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviewsDtos, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewsDto> getReviewById(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reviewsService.getReviewsById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @JsonView(AdvertReviewDetails.class)
    @GetMapping(value = "{id}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessagesDto>> getListMess(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reviewsService.getListMessagesDto(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PatchMapping(value = "{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchReview(@Validated(Existing.class)@PathVariable("id") Long id, @RequestBody ReviewsDto reviewsDto) throws EntityNotFoundException, DataIntegrityViolationException {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (reviewsDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reviewsService.update(reviewsDto);
        return new ResponseEntity(HttpStatus.CREATED);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteReview(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reviewsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
