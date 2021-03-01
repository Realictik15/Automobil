package com.automobil.backend.dto;

import com.automobil.backend.models.Messages;
import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsDto {

    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private Long idRevi;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private ClientsDto clientsDto;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private String marksTitle;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private String modelTitle;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private GenerationsDto generationsDto;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private ModificationsDto modificationsDto;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private Date daterel;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private String title;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private String text;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private String plus;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private String mins;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private Double raiting;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private Integer likes;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class})
    private Integer views;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class, AdvertReviewDetails.class})
    private String images;


}
