package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.mapStruct.MarksMapper;
import com.automobil.backend.models.Marks;
import com.automobil.backend.repository.MarksRepository;
import com.automobil.backend.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final MarksMapper marksMapper;

    @Autowired
    public MarksServiceImpl(MarksRepository marksRepository, MarksMapper marksMapper) {
        this.marksRepository = marksRepository;
        this.marksMapper = marksMapper;
    }


    @Override
    public List<MarksDto> listAll() {
        return null;
    }

    @Override
    public void save(MarksDto marksDto) {
        marksRepository.save(marksMapper.toMarks(marksDto));
    }

    @Override
    public void deleteById(Long id) {
        marksRepository.deleteById(id);
    }

    @Override
    public MarksDto getById(Long id) {
        return marksMapper.toMarksDTO(marksRepository.findById(id).get());
    }

    @Override
    public Marks getMarkByTitle(String title) {
        return marksRepository.getMarkByTitle(title);
    }

    @Override
    public void delete(MarksDto marksDto) {
        marksRepository.delete(marksMapper.toMarks(marksDto));
    }
}
