package com.automobil.backend.dto;

import com.automobil.backend.models.Clients;
import com.automobil.backend.models.Reviews;
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
@AllArgsConstructor
@NoArgsConstructor
public class MessagesDto {
    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Long idMess;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private ReviewsDto reviewsDto;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private ClientsDto clientsDto;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String text;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Moscow")
    private Date dateSend;
}
