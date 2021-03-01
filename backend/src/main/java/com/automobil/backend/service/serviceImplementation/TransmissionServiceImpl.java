package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.mapStruct.TransmissionMapper;
import com.automobil.backend.repository.TransmissionsRepository;
import com.automobil.backend.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransmissionServiceImpl implements TransmissionService {
    private final TransmissionMapper transmissionMapper;
    private final TransmissionsRepository transmissionsRepository;

    @Autowired
    TransmissionServiceImpl(TransmissionMapper transmissionMapper, TransmissionsRepository transmissionsRepository) {
        this.transmissionMapper = transmissionMapper;
        this.transmissionsRepository = transmissionsRepository;
    }

    @Override
    public List<TransmissionsDto> listAll() {
        return null;
    }

    @Override
    public void save(TransmissionsDto transmissionsDto) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public TransmissionsDto getById(Long id) {
        return transmissionMapper.toTransmissionDTO(transmissionsRepository.findById(id).get());
    }

    @Override
    public void delete(TransmissionsDto transmissionsDto) {

    }
}
