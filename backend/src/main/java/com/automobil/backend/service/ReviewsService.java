package com.automobil.backend.service;

import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.dto.ReviewsDto;
import com.automobil.backend.exeption.EntityNotFoundException;

import java.util.List;

public interface ReviewsService {
    List<ReviewsDto> getAll();

    ReviewsDto getReviewsById(Long id) throws Exception;

    List<MessagesDto> getListMessagesDto(Long id) throws Exception;

    void deleteById(Long id);

    void save(ReviewsDto reviewsDto) throws EntityNotFoundException;

    void update(ReviewsDto reviewsDto) throws EntityNotFoundException;

}
