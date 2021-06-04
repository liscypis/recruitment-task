package com.lisowski.pms.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserPasswordRequestDTO {

    @NotBlank(message = "id is required")
    private String id;

    @NotBlank(message = "oldPassword is required")
    private String oldPassword;

    @NotBlank(message = "newPassword is required")
    private String newPassword;

}
