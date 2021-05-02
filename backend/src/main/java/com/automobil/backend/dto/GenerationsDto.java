package com.automobil.backend.dto;

import com.automobil.backend.models.*;
import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerationsDto {
    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Long idGen;

    @NotNull(groups = {New.class, Existing.class,AdvertReviewDetails.class})
    @JsonView({AdminDetails.class})
    private String modelTitle;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String title;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class,AdvertReviewDetails.class})
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Moscow")
    private Date yearStartDate;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class,AdvertReviewDetails.class})
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Moscow")
    private Date yearEndDate;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String classOfCar;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String image;


}
