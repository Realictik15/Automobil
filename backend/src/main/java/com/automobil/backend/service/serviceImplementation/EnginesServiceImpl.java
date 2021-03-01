package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.mapStruct.EnginMapper;
import com.automobil.backend.repository.EnginesRepository;
import com.automobil.backend.service.EnginesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnginesServiceImpl implements EnginesService {
    private final EnginMapper enginMapper;
    private final EnginesRepository enginesRepository;

    @Autowired
    public EnginesServiceImpl(EnginMapper enginMapper, EnginesRepository enginesRepository) {
        this.enginMapper = enginMapper;
        this.enginesRepository = enginesRepository;
    }

    @Override
    public List<EnginesDto> listAll() {
        return enginMapper.toEnginesDTOs(enginesRepository.findAll());
    }

    @Override
    public void save(EnginesDto enginesDto) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public EnginesDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(EnginesDto enginesDto) {

    }
}
