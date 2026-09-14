package org.example.store_byspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private Integer productId;
    private String productName;
    private Integer quantity;


}
