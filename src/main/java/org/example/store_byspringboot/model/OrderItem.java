package org.example.store_byspringboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    @JsonBackReference("orderRef")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference("productRef")
    private Product product;

    public OrderItem() {
    }

}
