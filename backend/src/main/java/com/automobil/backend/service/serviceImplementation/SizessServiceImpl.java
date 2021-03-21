package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.SizessMapper;
import com.automobil.backend.models.Bodies;
import com.automobil.backend.models.Carbody;
import com.automobil.backend.models.Generations;
import com.automobil.backend.models.Sizess;
import com.automobil.backend.repository.CarbodyRepository;
import com.automobil.backend.repository.GenerationsRepository;
import com.automobil.backend.repository.SizessRepository;
import com.automobil.backend.service.SizessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizessServiceImpl implements SizessService {
    private final SizessMapper sizessMapper;
    private final SizessRepository sizessRepository;
    private final GenerationsRepository generationsRepository;
    private final CarbodyRepository carbodyRepository;

    @Autowired
    public SizessServiceImpl(SizessMapper sizessMapper, SizessRepository sizessRepository, GenerationsRepository generationsRepository, CarbodyRepository carbodyRepository) {
        this.sizessMapper = sizessMapper;
        this.sizessRepository = sizessRepository;
        this.generationsRepository = generationsRepository;
        this.carbodyRepository = carbodyRepository;
    }

    @Override
    public SizessDto getById(Long id) throws EntityNotFoundException {
        return sizessMapper.toSizessDTO(sizessRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Sizess")));
    }

    @Override
    public SizessDto getSize(Long idGen, Long idCarBody) throws EntityNotFoundException {
        Generations gen = generationsRepository.findById(idGen).orElseThrow(() -> new EntityNotFoundException(idGen, "Generations"));
        Carbody carbody = carbodyRepository.findById(idCarBody).orElseThrow(() -> new EntityNotFoundException(idCarBody, "CarBody"));
        return sizessMapper.toSizessDTO(gen.getBodies().stream().filter(carbody.getBodiesList()::contains).
            findFirst().orElseThrow(EntityNotFoundException::new).getSizess());
    }


    @Override
    public void save(SizessDto sizessDto) throws EntityNotFoundException {
        Sizess size= sizessMapper.toSizess(sizessDto);
        Generations gen = generationsRepository.findById(sizessDto.getIdGen()).orElseThrow(() -> new EntityNotFoundException(sizessDto.getIdGen(), "Generations"));
        Carbody carbody = carbodyRepository.findById(sizessDto.getIdCarBody()).orElseThrow(() -> new EntityNotFoundException(sizessDto.getIdCarBody(), "CarBody"));
        size.setBodies(new Bodies(null,carbody,gen,size));
        sizessRepository.save(size);
    }

    @Override
    public void deleteById(Long id) {
        sizessRepository.deleteById(id);
    }
}
