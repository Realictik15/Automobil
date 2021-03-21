package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.dto.ReviewsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.MessagesMapper;
import com.automobil.backend.mapStruct.ReviewsMapper;
import com.automobil.backend.models.Reviews;
import com.automobil.backend.repository.*;
import com.automobil.backend.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private final ReviewsMapper reviewsMapper;
    private final ReviewsRepository reviewsRepository;
    private final MessagesMapper messagesMapperr;
    private final ClientsRepository clientsRepository;
    private final MarksRepository marksRepository;
    private final ModelsRepository modelsRepository;
    private final GenerationsRepository generationsRepository;
    private final ModificationsRepository modificationsRepository;

    @Autowired
    public ReviewsServiceImpl(ReviewsMapper reviewsMapper, ReviewsRepository reviewsRepository,
                              MessagesMapper messagesMapperr, ClientsRepository clientsRepository,
                              MarksRepository marksRepository, ModelsRepository modelsRepository,
                              GenerationsRepository generationsRepository, ModificationsRepository modificationsRepository) {
        this.reviewsMapper = reviewsMapper;
        this.reviewsRepository = reviewsRepository;
        this.messagesMapperr = messagesMapperr;
        this.clientsRepository = clientsRepository;
        this.marksRepository = marksRepository;
        this.modelsRepository = modelsRepository;
        this.generationsRepository = generationsRepository;
        this.modificationsRepository = modificationsRepository;
    }

    @Override
    public List<ReviewsDto> getAll() {
        return reviewsMapper.toReviewsDTOs(reviewsRepository.findAll());
    }

    @Override
    public ReviewsDto getReviewsById(Long id) throws Exception {
        return reviewsMapper.toReviewsDTO(reviewsRepository.findById(id).orElseThrow(Exception::new));
    }

    @Override
    public List<MessagesDto> getListMessagesDto(Long id) throws Exception {
        Reviews reviews = reviewsRepository.findById(id).orElseThrow(Exception::new);
        return messagesMapperr.toMessageDTOs(reviews.getMessages());
    }

    @Override
    public void deleteById(Long id) {
        reviewsRepository.deleteById(id);
    }

    @Override
    public void save(ReviewsDto reviewsDto) throws EntityNotFoundException {
        Reviews review = reviewsMapper.toReviews(reviewsDto);
        review.setClients(clientsRepository.findById(reviewsDto.getClientsDto().getIdUser()).orElseThrow(EntityNotFoundException::new));
        review.setMark(marksRepository.getMarkByTitle(reviewsDto.getMarksTitle()).orElseThrow(EntityNotFoundException::new));
        review.setModel(modelsRepository.getModelsByTitle(reviewsDto.getModelTitle()).orElseThrow(EntityNotFoundException::new));
        review.setGenerations(generationsRepository.findById(reviewsDto.getGenerationsDto().
            getIdGen()).orElseThrow(EntityNotFoundException::new));
        review.setModification(modificationsRepository.findById(reviewsDto.getModificationsDto().
            getIdModif()).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void update(ReviewsDto reviewsDto) throws EntityNotFoundException {
        Reviews newreview = reviewsRepository.findById(reviewsDto.getIdRevi()).orElseThrow(EntityNotFoundException::new);
        newreview.setDaterel(reviewsDto.getDaterel());
        newreview.setTitle(reviewsDto.getTitle());
        newreview.setText(newreview.getText());
        newreview.setPlus(reviewsDto.getPlus());
        newreview.setMins(reviewsDto.getMins());
        newreview.setLikes(reviewsDto.getLikes());
        newreview.setViews(reviewsDto.getViews());
        reviewsRepository.save(newreview);
    }
}
