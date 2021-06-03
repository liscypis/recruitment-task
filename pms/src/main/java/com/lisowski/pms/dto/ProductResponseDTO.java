package com.lisowski.pms.dto;


import lombok.Data;


@Data
public class ProductResponseDTO {

    private String id;
    private String productName;
    private double netPrice;
    private double grossPrice;
    private String description;
    private int vat;
    private CategoryResponseDTO category;
    private boolean available;
}
