package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.CompleteSetMapper;
import com.automobil.backend.models.CompleteSets;
import com.automobil.backend.repository.CompletesetsRepository;
import com.automobil.backend.service.CompleteSetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompleteSetsServiceImpl implements CompleteSetsService {
    private final CompleteSetMapper completeSetMapper;
    private final CompletesetsRepository completesetsRepository;

    @Autowired
    public CompleteSetsServiceImpl(CompleteSetMapper completeSetMapper, CompletesetsRepository completesetsRepository) {
        this.completeSetMapper = completeSetMapper;
        this.completesetsRepository = completesetsRepository;
    }

    @Override
    public void save(CompleteSetsDto completeSetsDto) {
        completesetsRepository.save(completeSetMapper.toCompleteSets(completeSetsDto));
    }

    @Override
    public void deleteById(Long id) {
        completesetsRepository.deleteById(id);
    }

    @Override
    public CompleteSetsDto getById(Long id) throws EntityNotFoundException {
        return completeSetMapper.toCompleteSetsDTO(completesetsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "CompleSets")));
    }

    @Override
    public List<CompleteSetsDto> getAll() {
        return completeSetMapper.toCompleteSetsDTOs(completesetsRepository.findAll());
    }
}
