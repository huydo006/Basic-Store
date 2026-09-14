package org.example.store_byspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private Integer productId;
    private Integer quantity;

    public OrderItemRequest() {
    }
}
