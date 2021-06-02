package com.lisowski.pms.dto;

import com.lisowski.pms.entity.Category;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

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
