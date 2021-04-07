package com.automobil.backend.models;

import com.automobil.backend.dto.ClassesDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.mapStruct.ModelsMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import javax.management.relation.Role;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ModelsTest {
    private ModelsMapper modelsMapper = Mappers.getMapper(ModelsMapper.class);

    @Test
    void userModelAdd() throws ParseException {
        ClassesDto classes = new ClassesDto();
        ModelDto modelDto = new ModelDto((long)1,"AUDI",(long)4,"ds","dsd","dsds");
        Models models= modelsMapper.toModels(modelDto);
        assertEquals(modelDto.getMarkTitle(), models.getMark().getTitle());
    }
    @Test
    void usr(){
        Roles r = Roles.USER;
        System.out.println(r.name());
    }

}
