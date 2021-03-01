package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.models.Bodies;
import com.automobil.backend.models.Models;
import com.automobil.backend.models.Sizess;
import com.automobil.backend.repository.BodiesRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SizessMapper {
    @Autowired
    private BodiesRepository bodiesRepository;

    @Mappings({
        @Mapping(source = "bodies.idBodyGen", target = "idBody"),
    })
    public abstract SizessDto toSizessDTO(Sizess sizess);

    public abstract List<SizessDto> toSizessDTOs(List<Sizess> sizesses);

   public Sizess toSizess(SizessDto sizessDto){
       if ( sizessDto == null ) {
           return null;
       }

       Sizess sizess = new Sizess();

       sizess.setBodies(bodiesRepository.findById(sizessDto.getIdBody()).get());
       sizess.setIdSiz( sizessDto.getIdSiz() );
       sizess.setLenght( sizessDto.getLenght() );
       sizess.setWidht( sizessDto.getWidht() );
       sizess.setHight( sizessDto.getHight() );
       sizess.setWeight( sizessDto.getWeight() );
       sizess.setWheelbase( sizessDto.getWheelbase() );
       sizess.setClearance( sizessDto.getClearance() );
       sizess.setWheelsSize( sizessDto.getWheelsSize() );
       sizess.setTrunkWight( sizessDto.getTrunkWight() );

       return sizess;
   }
}

