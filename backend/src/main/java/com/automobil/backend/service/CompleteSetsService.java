package com.automobil.backend.service;

import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.models.CompleteSets;

import java.util.List;

public interface CompleteSetsService {
    List<CompleteSetsDto> getListCompl();

    CompleteSetsDto getCompl(Long id);
}
