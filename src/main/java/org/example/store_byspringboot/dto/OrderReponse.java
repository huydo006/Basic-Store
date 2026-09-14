package org.example.store_byspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class OrderReponse {
    private String customerName;
    private String customerEmail;
    private String status;
    private BigDecimal total_amount;
    private java.util.List<OrderItemRequest> orderItems;

}
