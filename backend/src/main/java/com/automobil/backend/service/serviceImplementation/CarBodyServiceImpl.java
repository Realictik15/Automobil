package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.CarbodyDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.CarbodyMapper;
import com.automobil.backend.repository.CarbodyRepository;
import com.automobil.backend.service.CarBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBodyServiceImpl implements CarBodyService {
    private final CarbodyRepository carbodyRepository;
    private final CarbodyMapper carbodyMapper;


    @Autowired
    public CarBodyServiceImpl(CarbodyRepository carbodyRepository, CarbodyMapper carbodyMapper) {
        this.carbodyRepository = carbodyRepository;
        this.carbodyMapper = carbodyMapper;
    }

    @Override
    public CarbodyDto getCarBody(Long id) throws EntityNotFoundException {
        return carbodyMapper.toCarBodyDTO(carbodyRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "CarBody")));
    }

    @Override
    public List<CarbodyDto> getListCarBody() {
        return carbodyMapper.toCarBodyDTOs(carbodyRepository.findAll());
    }


    @Override
    public CarbodyDto getCarBodyByTitle(String title) throws EntityNotFoundException {
        return carbodyMapper.toCarBodyDTO(carbodyRepository.getCarBodyByTitle(title).
            orElseThrow(() -> new EntityNotFoundException(title, "CarBody")));
    }
}
