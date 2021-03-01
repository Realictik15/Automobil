package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.mapStruct.ModificationMapper;
import com.automobil.backend.repository.ModificationsRepository;
import com.automobil.backend.service.ModificationsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModificationsServiceImpl implements ModificationsService {
    private final ModificationsRepository modificationsRepository;
    private final ModificationMapper modificationMapper;

    public ModificationsServiceImpl(ModificationsRepository modificationsRepository, ModificationMapper modificationMapper) {
        this.modificationsRepository = modificationsRepository;
        this.modificationMapper = modificationMapper;
    }

    @Override
    public List<ModificationsDto> listAll() {
        return null;
    }

    @Override
    public void save(ModificationsDto modificationsDto) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ModificationsDto getById(Long id) {
        return modificationMapper.toModificationDTO(modificationsRepository.findById(id).get());
    }

    @Override
    public void delete(ModificationsDto modificationsDto) {

    }
}
