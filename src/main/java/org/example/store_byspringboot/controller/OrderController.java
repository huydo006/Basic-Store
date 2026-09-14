package org.example.store_byspringboot.controller;

import org.example.store_byspringboot.dto.OrderResponse;
import org.example.store_byspringboot.dto.OrderRequest;
import org.example.store_byspringboot.model.Orders;
import org.example.store_byspringboot.service.OrderService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Orders createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderDetailbyId(@PathVariable Integer id){
        return orderService.getOrderbyId(id);

    }
    @PutMapping("/{id}")
    public void cancelOrder(@PathVariable Integer id){
        orderService.cancelOrder(id);
    }

}
