package com.automobil.backend.dto;

import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarksDto {
    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Long idMark;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class})
    private String countriesDto;

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
