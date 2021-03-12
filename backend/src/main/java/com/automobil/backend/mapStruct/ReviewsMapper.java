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
public interface ReviewsMapper {

    @Mappings({@Mapping(source = "clients", target = "clientsDto"),
        @Mapping(source = "mark.title", target = "marksTitle"),
        @Mapping(source = "model.title", target = "modelTitle"),
        @Mapping(source = "generations", target = "generationsDto"),
        @Mapping(source = "modification", target = "modificationsDto")
    })
    ReviewsDto toReviewsDTO(Reviews reviews);

    List<ReviewsDto> toReviewsDTOs(List<Reviews> reviews);

    @Mappings({@Mapping(target = "clients", source = "clientsDto"),
        @Mapping(target = "mark.title", source = "marksTitle"),
        @Mapping(target = "model.title", source = "modelTitle"),
        @Mapping(target = "generations", source = "generationsDto"),
        @Mapping(target = "modification", source = "modificationsDto")
    })
    Reviews toReviews(ReviewsDto reviewsDto);
}