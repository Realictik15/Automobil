package com.automobil.backend.models;

import com.automobil.backend.dto.ClassesDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.mapStruct.ModelsMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import javax.management.relation.Role;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ModelsTest {
    private ModelsMapper modelsMapper = Mappers.getMapper(ModelsMapper.class);

    @Test
    void userModelAdd() throws ParseException {
        ClassesDto classes = new ClassesDto();
        ModelDto modelDto = new ModelDto((long) 1, "AUDI", (long) 4, "ds", "dsd", "dsds");
        Models models = modelsMapper.toModels(modelDto);
        assertEquals(modelDto.getMarkTitle(), models.getMark().getTitle());
    }

    @Test
    void usr() {
        Roles r = Roles.USER;
        System.out.println(r.name());
    }

    @Test
    void calculationNalog() {
        int power = 188;
        int nalog = 0;
        NavigableMap<Integer, Integer> table = new TreeMap<Integer, Integer>();
        table.put(0, 12);
        table.put(100, 25);
        table.put(150, 45);
        table.put(125, 35);
        table.put(175, 50);
        table.put(200, 65);
        table.put(225, 75);
        table.put(250, 150);
        nalog = (int) (1.1 * power * table.floorEntry(power).getValue());
        System.out.println(nalog);

    }

    @Test
    void split() {
        String path = "C:/Users/Artem/IdeaProjects/Automobil/frontend/src/6927b512-9af0-44e1-a813-f1947a7c65e6icfihdi1oPA.jpg, C:/Users/Artem/IdeaProjects/Automobil/frontend/src/assets/e37b295c-4fea-4b32-97d1-88a5bfdac016ja-vam-zapreshchaju-srat-768x491.jpg";
        List<String> res = (Arrays.asList(path.split(",")));
        int index;
        List<String> s = new ArrayList<>();
        for (String str : res) {
            index = str.indexOf("assets/");
            if(index!=-1) {
                s.add(str.substring(index));
            }
        }
        System.out.println(s);
    }
    @Test
    void dateTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(date);
    }
}
