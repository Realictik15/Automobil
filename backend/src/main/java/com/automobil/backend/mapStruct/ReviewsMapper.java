package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.dto.ReviewsDto;
import com.automobil.backend.models.Marks;
import com.automobil.backend.models.Modifications;
import com.automobil.backend.models.Reviews;
import com.automobil.backend.repository.MarksRepository;
import com.automobil.backend.service.ModelsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenerationsMapper.class, MarksMapper.class, ModelsMapper.class, CliensMapper.class, ModificationMapper.class})
public abstract class ReviewsMapper {
    @Autowired
    private GenerationsMapper generationsMapper;
    @Autowired
    private CliensMapper cliensMapper;
    @Autowired
    private ModificationMapper modificationMapper;
    @Autowired
    private ModelsService modelsService;
    @Autowired
    private MarksRepository marksRepository;

    @Mappings({@Mapping(source = "clients", target = "clientsDto"),
        @Mapping(source = "mark.title", target = "marksTitle"),
        @Mapping(source = "model.title", target = "modelTitle"),
        @Mapping(source = "generations", target = "generationsDto"),
        @Mapping(source = "modification", target = "modificationsDto")
    })
    abstract public ReviewsDto toReviewsDTO(Reviews reviews);

    abstract public List<ReviewsDto> toReviewsDTOs(List<Reviews> reviews);

    @Mappings({@Mapping(target = "clients", source = "clientsDto"),
        @Mapping(target = "mark.title", source = "marksTitle"),
        @Mapping(target = "model.title", source = "modelTitle"),
        @Mapping(target = "generations", source = "generationsDto"),
        @Mapping(target = "modification", source = "modificationsDto")
    })
    public Reviews toReviews(ReviewsDto reviewsDto) {
        if (reviewsDto == null) {
            return null;
        }

        Reviews reviews = new Reviews();

        reviews.setMark(marksRepository.getMarkByTitle(reviewsDto.getMarksTitle()));
        reviews.setModel(modelsService.getMarkByTitle(reviewsDto.getModelTitle()));
        reviews.setClients(cliensMapper.toClients(reviewsDto.getClientsDto()));
        reviews.setGenerations(generationsMapper.toGenerations(reviewsDto.getGenerationsDto()));
        reviews.setModification(modificationMapper.toModification(reviewsDto.getModificationsDto()));
        reviews.setIdRevi(reviewsDto.getIdRevi());
        reviews.setDaterel(reviewsDto.getDaterel());
        reviews.setTitle(reviewsDto.getTitle());
        reviews.setText(reviewsDto.getText());
        reviews.setPlus(reviewsDto.getPlus());
        reviews.setMins(reviewsDto.getMins());
        reviews.setRaiting(reviewsDto.getRaiting());
        reviews.setLikes(reviewsDto.getLikes());
        reviews.setViews(reviewsDto.getViews());
        reviews.setImages(reviewsDto.getImages());

        return reviews;
    }
}