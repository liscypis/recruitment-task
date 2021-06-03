package com.lisowski.pms.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "password is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
}
