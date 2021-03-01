package com.automobil.backend.dto;

import com.automobil.backend.models.Parts;
import com.automobil.backend.transfer.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientsDto {
    @Null(groups = {New.class}, message = "The field must be empty")
    @NotNull(groups = {Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private Long idUser;
//
//    @NotNull(groups = {New.class})
//    @JsonView({Details.class, AdminDetails.class})
//    private Parts part;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String firstName;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class, AdvertReviewDetails.class})
    private String lastName;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class})
    private Date bornDay;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({AdminDetails.class})
    private String login;

    @NotNull(groups = {New.class})
    @Null(groups = {Existing.class})
    @JsonView({AdminDetails.class})
    private String pass;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({AdminDetails.class})
    private String emale;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class})
    private Long telephone;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class})
    private Integer driveExp;

    @NotNull(groups = {New.class, Existing.class})
    @JsonView({Details.class, AdminDetails.class})
    private Double money;

}
