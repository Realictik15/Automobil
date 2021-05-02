package com.automobil.backend.dto;

import lombok.Data;

@Data
public class ReportDto {
    private Integer id;
    private Long licenseNumber;
    private String fullName;
    private String shortName;
}
