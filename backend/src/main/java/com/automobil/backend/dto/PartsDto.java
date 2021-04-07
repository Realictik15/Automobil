package com.automobil.backend.dto;


import com.automobil.backend.transfer.AdminDetails;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartsDto {
    @JsonView({AdminDetails.class})
    private Long idPart;

    @JsonView({AdminDetails.class})
    private String title;
}
