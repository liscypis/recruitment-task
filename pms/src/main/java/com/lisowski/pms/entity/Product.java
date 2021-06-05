package com.lisowski.pms.entity;

import com.lisowski.pms.utils.Utils;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {
    @Id
    private String id;
    private String productName;
    private double netPrice;
    @Getter
    private double grossPrice;
    private String description;
    private int vat;
    private Category category;
    private boolean available;

    public void updateGrossPrice() {
        if (this.vat == 0)
            this.grossPrice = this.netPrice;
        else{
            this.grossPrice = this.netPrice + this.netPrice * this.vat / 100;
            this.grossPrice = Utils.round(this.grossPrice, 2);
        }

    }
}
