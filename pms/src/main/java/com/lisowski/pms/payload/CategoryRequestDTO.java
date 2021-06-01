package com.lisowski.pms.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;
}
