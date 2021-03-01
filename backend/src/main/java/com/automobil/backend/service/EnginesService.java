package com.automobil.backend.service;

//import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.models.Clients;
import com.automobil.backend.models.Engines;

import java.util.List;

public interface EnginesService {
    List<EnginesDto> listAll();

    void save(EnginesDto enginesDto);

    void deleteById(Long id);

    EnginesDto getById(Long id);

    void delete(EnginesDto enginesDto);


}
