package com.automobil.backend.dto;

import com.automobil.backend.models.Possibles;
import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModificationsDto {
    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Long idModif;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class})
    private GenerationsDto generationsDto;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private EnginesDto enginesDto;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private TransmissionsDto transmissionsDto;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String title;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Integer sits;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Integer doors;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private List<Long> idComplSet;
}
