package com.lisowski.pms.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MessageRequestDTO {

    @NotBlank(message = "subject is required")
    private String subject;
    @NotBlank(message = "message is required")
    private String message;
    @NotBlank(message = "userId is required")
    private String userId;
}
