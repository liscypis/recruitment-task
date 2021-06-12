package com.lisowski.pms.dto;

import com.lisowski.pms.entity.User;
import lombok.Data;



@Data
public class MessageResponseDTO {
    private String id;
    private String subject;
    private String message;
    private UserResponseDTO user;
}
