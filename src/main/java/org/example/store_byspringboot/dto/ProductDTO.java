package org.example.store_byspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductDTO {
    private Integer productId;
    private String productName;
    private String description;
    private BigDecimal price;

}
