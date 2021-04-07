package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.CountriesDto;
import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.models.Countries;
import com.automobil.backend.models.Engines;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountriesMapper {
    @Mapping(source = "idCountry", target = "id")
    CountriesDto toCountriesDTO(Countries countries);

    List<CountriesDto> toCountriesDTOs(List<Countries> countries);
    @Mapping(source = "id", target = "idCountry")
    Countries toCountries(CountriesDto countriesDto);

}
