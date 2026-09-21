package org.example.store_byspringboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ProductResponse {
    private Integer productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    private Integer id;
//    private String name;
//    private String description;
//    private BigDecimal price;
//    private Integer stock;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

}
