package com.automobil.backend.dto;

import lombok.Data;

@Data
public class RestrictoinApi {
    private String yearRestrict;
    private String city;
    private String organization;
    private String typeRestrict;
    private String reasons;
}
