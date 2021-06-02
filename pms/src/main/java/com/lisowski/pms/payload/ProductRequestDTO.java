package com.lisowski.pms.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "productName is required")
    private String productName;

    @Min(value = 0, message = "netPrice should not be less than 0")
    @NotNull(message = "netPrice is required")
    private double netPrice;

    @NotBlank(message = "description is required")
    private String description;

    @Min(value = 0, message = "vat should not be less than 0")
    @Max(value = 100, message = "vat should not be greater than 100")
    @NotNull(message = "vat is required")
    private int vat;

    private boolean available;
}
