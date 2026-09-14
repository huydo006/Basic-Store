package org.example.store_byspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String customerName;
    private String customerEmail;
    private String status;
    private java.util.List<OrderItemRequest> orderItems;
}
