package com.lisowski.pms.dto;

import com.lisowski.pms.entity.ERole;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private ERole role;
}
