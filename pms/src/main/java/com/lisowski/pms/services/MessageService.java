package com.lisowski.pms.services;

import com.lisowski.pms.dto.MessageResponseDTO;
import com.lisowski.pms.payload.MessageRequestDTO;

import java.util.List;

public interface MessageService {
    List<MessageResponseDTO> getAllMessages();
    void addMessage(MessageRequestDTO requestDTO);


}
