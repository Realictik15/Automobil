package com.automobil.backend.dto;

import lombok.Data;

@Data
public class AccidentsApi {
    private String yearAccident;
    private String accidentType;
    private String place;
    private String damageDescription;
    private String urlImages;

}
