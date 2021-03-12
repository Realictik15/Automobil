package com.automobil.backend.service;

import com.automobil.backend.dto.MessagesDto;

import java.util.List;

public interface MessagesService {
    List<MessagesDto> getListMessages();

}
