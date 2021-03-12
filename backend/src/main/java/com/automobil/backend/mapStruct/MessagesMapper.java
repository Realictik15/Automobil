package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.models.Messages;
import com.automobil.backend.models.Models;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

public interface MessagesMapper {
    @Mappings({
        @Mapping(source = "reviews", target = "reviewsDto"),
        @Mapping(source = "clients", target = "clientsDto")
    })
    MessagesDto toMessageDTO(Messages message) ;


    List<MessagesDto> toMessageDTOs(List<Messages> messages);
    @Mappings({
        @Mapping(target = "reviews", source = "reviewsDto"),
        @Mapping(target = "clients", source = "clientsDto")
    })
    Messages toMessage(MessagesDto messagesDto) ;

}
