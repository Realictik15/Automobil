package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.MarksMapper;
import com.automobil.backend.mapStruct.ModelsMapper;
import com.automobil.backend.models.Marks;
import com.automobil.backend.models.Models;
import com.automobil.backend.repository.CountriesRepository;
import com.automobil.backend.repository.MarksRepository;
import com.automobil.backend.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final MarksMapper marksMapper;
    private final CountriesRepository countriesRepository;
    private final ModelsMapper modelsMapper;

    @Autowired
    public MarksServiceImpl(MarksRepository marksRepository, MarksMapper marksMapper, CountriesRepository countriesRepository, ModelsMapper modelsMapper) {
        this.marksRepository = marksRepository;
        this.marksMapper = marksMapper;
        this.countriesRepository = countriesRepository;
        this.modelsMapper = modelsMapper;
    }

    @Override
    public List<MarksDto> listAll() {

        return marksMapper.toMarksDTOs(marksRepository.findAll().stream().sorted(Comparator.comparing(Marks::getTitle)).collect(Collectors.toList()));
    }

    @Override
    public void save(MarksDto marksDto) throws EntityNotFoundException {
        Marks mark = marksMapper.toMarks(marksDto);
        mark.setCountries(countriesRepository.getCountriesByTitle(marksDto.
            getCountriesDto()).orElseThrow(()->new EntityNotFoundException(marksDto.getCountriesDto(),"Country")));
        marksRepository.save(mark);

    }

    @Override
    public void deleteById(Long id) {
        marksRepository.deleteById(id);
    }

    @Override
    public MarksDto getById(Long id) throws EntityNotFoundException {
        return marksMapper.toMarksDTO(marksRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Marks")));
    }

    @Override
    public Marks getMarkByTitle(String title) throws EntityNotFoundException {
        return marksRepository.getMarkByTitle(title).
            orElseThrow(() -> new EntityNotFoundException(title, "Models"));
    }


    @Override
    public MarksDto toMarksDTO(Marks marks) {
        return marksMapper.toMarksDTO(marks);
    }

    @Override
    public List<ModelDto> getListModels(Long id) throws EntityNotFoundException {
        Marks marks = marksRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Marks"));
        return modelsMapper.toModelDTOs(marks.getModels().stream().sorted(Comparator.comparing(Models::getTitle)).collect(Collectors.toList()));
    }
}

