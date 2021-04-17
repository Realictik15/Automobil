package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Mapper(componentModel = "spring", uses = {MarksMapper.class, ModelsMapper.class, GenerationsMapper.class, ModificationMapper.class, CarbodyMapper.class, CliensMapper.class})
public abstract class AdvertismetMapper {

    //    @Mappings({
//        @Mapping(source = "carbody.title", target = "carbodyTitle"),
//        @Mapping(source = "clients", target = "clientsDto"),
//        @Mapping(source = "mark.title", target = "marksTitle"),
//        @Mapping(source = "model.title", target = "modelTitle"),
//        @Mapping(source = "generations", target = "generationsDto"),
//        @Mapping(source = "modification", target = "modificationsDto")
//
//    })
//    AdvertismentDto toAdvertismentDTO(Advertisments advertisments);
//
//   public List<AdvertismentDto> toAdvertismentDTOs(List<Advertisments> advertisments);
//
//    @Mappings({
//        @Mapping(target = "carbody.title", source = "carbodyTitle"),
//        @Mapping(target = "clients", source = "clientsDto"),
//        @Mapping(target = "mark.title", source = "marksTitle"),
//        @Mapping(target = "model.title", source = "modelTitle"),
//        @Mapping(target = "generations", source = "generationsDto"),
//        @Mapping(target = "modification", source = "modificationsDto")
//
//    })
//   public Advertisments toAdvertisment(AdvertismentDto advertismentDto){
//    }
    @Autowired
    private GenerationsMapper generationsMapper;
    @Autowired
    private ModificationMapper modificationMapper;
    @Autowired
    private CliensMapper cliensMapper;

    public AdvertismentDto toAdvertismentDTO(Advertisments advertisments) {
        if (advertisments == null) {
            return null;
        }

        AdvertismentDto advertismentDto = new AdvertismentDto();
        advertismentDto.setCarbodyTitle(advertismentsCarbodyTitle(advertisments));
        advertismentDto.setClientsDto(cliensMapper.toClientsDTO(advertisments.getClients()));
        advertismentDto.setMarksTitle(advertismentsMarkTitle(advertisments));
        advertismentDto.setModelTitle(advertismentsModelTitle(advertisments));
        advertismentDto.setGenerationsDto(generationsMapper.toGenerationsDTO(advertisments.getGenerations()));
        advertismentDto.setModificationsDto(modificationMapper.toModificationDTO(advertisments.getModification()));
        advertismentDto.setIdAdvert(advertisments.getIdAdvert());
        advertismentDto.setAvailable(advertisments.getAvailable());
        advertismentDto.setPrice(advertisments.getPrice());
        advertismentDto.setColour(advertisments.getColour());
        advertismentDto.setMileage(advertisments.getMileage());
        advertismentDto.setBroken(advertisments.getBroken());
        advertismentDto.setCity(advertisments.getCity());
        advertismentDto.setPlace(advertisments.getPlace());
        advertismentDto.setPhone(advertisments.getPhone());
        advertismentDto.setSwap(advertisments.getSwap());
        advertismentDto.setPts(advertisments.getPts());
        advertismentDto.setOwners(advertisments.getOwners());
        advertismentDto.setGosnumber(advertisments.getGosnumber());
        advertismentDto.setBuyday(advertisments.getBuyday());
        advertismentDto.setDayofwarranty(advertisments.getDayofwarranty());
        advertismentDto.setVin(advertisments.getVin());
        advertismentDto.setSts(advertisments.getSts());
        advertismentDto.setCommentns(advertisments.getCommentns());
        advertismentDto.setNalog(calculationNalog(advertisments.getPrice(),advertisments.getModification().getEngines().getPower()));
        advertismentDto.setImagesList(setListImg(advertisments.getImages()));
        advertismentDto.getClientsDto().setCountOfAdvert(advertisments.getClients().getAdvertisments().size());

        return advertismentDto;
    }

    public List<AdvertismentDto> toAdvertismentDTOs(List<Advertisments> advertisments) {
        if (advertisments == null) {
            return null;
        }
        List<AdvertismentDto> list = new ArrayList<AdvertismentDto>(advertisments.size());
        for (Advertisments advertisments1 : advertisments) {
            list.add(toAdvertismentDTO(advertisments1));
        }
        return list;
    }

    public Advertisments toAdvertisment(AdvertismentDto advertismentDto) {
        if (advertismentDto == null) {
            return null;
        }
        Advertisments advertisments = new Advertisments();

        advertisments.setCarbody(advertismentDtoToCarbody(advertismentDto));
        advertisments.setMark(advertismentDtoToMarks(advertismentDto));
        advertisments.setModel(advertismentDtoToModels(advertismentDto));
        advertisments.setClients(cliensMapper.toClients(advertismentDto.getClientsDto()));
        advertisments.setGenerations(generationsMapper.toGenerations(advertismentDto.getGenerationsDto()));
        advertisments.setModification(modificationMapper.toModification(advertismentDto.getModificationsDto()));
        advertisments.setIdAdvert(advertismentDto.getIdAdvert());
        advertisments.setAvailable(advertismentDto.getAvailable());
        advertisments.setPrice(advertismentDto.getPrice());
        advertisments.setColour(advertismentDto.getColour());
        advertisments.setMileage(advertismentDto.getMileage());
        advertisments.setBroken(advertismentDto.getBroken());
        advertisments.setCity(advertismentDto.getCity());
        advertisments.setPlace(advertismentDto.getPlace());
        advertisments.setPhone(advertismentDto.getPhone());
        advertisments.setSwap(advertismentDto.getSwap());
        advertisments.setPts(advertismentDto.getPts());
        advertisments.setOwners(advertismentDto.getOwners());
        advertisments.setGosnumber(advertismentDto.getGosnumber());
        advertisments.setBuyday(advertismentDto.getBuyday());
        advertisments.setDayofwarranty(advertismentDto.getDayofwarranty());
        advertisments.setVin(advertismentDto.getVin());
        advertisments.setSts(advertismentDto.getSts());
        advertisments.setCommentns(advertismentDto.getCommentns());

        return advertisments;
    }

    private String advertismentsCarbodyTitle(Advertisments advertisments) {
        if (advertisments == null) {
            return null;
        }
        Carbody carbody = advertisments.getCarbody();
        if (carbody == null) {
            return null;
        }
        String title = carbody.getTitle();
        if (title == null) {
            return null;
        }
        return title;
    }

    private String advertismentsMarkTitle(Advertisments advertisments) {
        if (advertisments == null) {
            return null;
        }
        Marks mark = advertisments.getMark();
        if (mark == null) {
            return null;
        }
        String title = mark.getTitle();
        if (title == null) {
            return null;
        }
        return title;
    }

    private String advertismentsModelTitle(Advertisments advertisments) {
        if (advertisments == null) {
            return null;
        }
        Models model = advertisments.getModel();
        if (model == null) {
            return null;
        }
        String title = model.getTitle();
        if (title == null) {
            return null;
        }
        return title;
    }

    protected Carbody advertismentDtoToCarbody(AdvertismentDto advertismentDto) {
        if (advertismentDto == null) {
            return null;
        }
        Carbody carbody = new Carbody();
        carbody.setTitle(advertismentDto.getCarbodyTitle());
        return carbody;
    }

    protected Marks advertismentDtoToMarks(AdvertismentDto advertismentDto) {
        if (advertismentDto == null) {
            return null;
        }
        Marks marks = new Marks();
        marks.setTitle(advertismentDto.getMarksTitle());
        return marks;
    }

    protected Models advertismentDtoToModels(AdvertismentDto advertismentDto) {
        if (advertismentDto == null) {
            return null;
        }
        Models models = new Models();
        models.setTitle(advertismentDto.getModelTitle());
        return models;
    }
    public List<String> setListImg(String path) {
        List<String> tmp = (Arrays.asList(path.split(",")));
        List<String> res = new ArrayList<>();
        int index;
        for (String str : tmp) {
            index = str.indexOf("assets/");
            if(index!=-1) {
                res.add(str.substring(index));
            }
        }
        if(res.isEmpty()){
            res.add("assets/default.png");
        }
        return res;
    }

    public Integer calculationNalog(double price, int power) {
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

        if (price >= 3000000) {
            nalog = (int) (1.1 * power * table.floorEntry(power).getValue());
        } else {
            nalog =  (power * table.floorEntry(power).getValue());
        }
        return nalog;
    }
}

