package com.lisowski.pms.payload;

import com.lisowski.pms.entity.ERole;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequestDTO {

    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "email is required")
    @Email
    private String email;
    @NotBlank(message = "phoneNumber is required")

    @Size(max = 9, min = 9)
    private String phoneNumber;
    @NotNull(message = "role is required")
    private ERole role;
}
