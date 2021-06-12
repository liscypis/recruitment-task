package com.lisowski.pms.services.servicesImpl;

import com.lisowski.pms.dto.MessageResponseDTO;
import com.lisowski.pms.entity.Message;
import com.lisowski.pms.entity.User;
import com.lisowski.pms.payload.MessageRequestDTO;
import com.lisowski.pms.repository.MessageRepository;
import com.lisowski.pms.repository.UserRepository;
import com.lisowski.pms.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public List<MessageResponseDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(message -> modelMapper.map(message, MessageResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addMessage(MessageRequestDTO requestDTO) {
        User user = getUserFromDB(requestDTO.getUserId());
        Message message = new Message();
        message.setMessage(requestDTO.getMessage());
        message.setSubject(requestDTO.getSubject());
        message.setUser(user);

        messageRepository.save(message);
    }

    private User getUserFromDB(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid id"));
    }
}
