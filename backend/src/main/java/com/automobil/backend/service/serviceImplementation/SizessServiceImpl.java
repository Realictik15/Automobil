package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.SizessMapper;
import com.automobil.backend.repository.SizessRepository;
import com.automobil.backend.service.SizessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizessServiceImpl implements SizessService {
    private final SizessMapper sizessMapper;
    private final SizessRepository sizessRepository;

    @Autowired
    public SizessServiceImpl(SizessMapper sizessMapper, SizessRepository sizessRepository) {
        this.sizessMapper = sizessMapper;
        this.sizessRepository = sizessRepository;
    }

    @Override
    public SizessDto getBId(Long id) throws EntityNotFoundException {
        return sizessMapper.toSizessDTO(sizessRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Sizess")));
    }


    @Override
    public void seve(SizessDto sizessDto) {
        sizessRepository.save(sizessMapper.toSizess(sizessDto));
    }

    @Override
    public void delete(Long id) {
        sizessRepository.deleteById(id);
    }
}
