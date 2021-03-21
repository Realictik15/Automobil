package com.automobil.backend.dto;

import com.automobil.backend.models.Advertisments;
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
public class ComparisonsDto {

    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Long idCompar;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private AdvertismentDto advertismentDto;

    private Long idClient;
}
