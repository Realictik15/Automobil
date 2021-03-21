package com.automobil.backend.dto;

import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelDto {

    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Long idModel;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class,Details.class,})
    private String markTitle;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class})
    private Long idClass;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String title;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class})
    private String info;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String image;
}
