package org.example.store_byspringboot.service;

import jakarta.transaction.Transactional;
import org.example.store_byspringboot.dto.OrderItemResponse;
import org.example.store_byspringboot.dto.OrderItemRequest;
import org.example.store_byspringboot.dto.OrderResponse;
import org.example.store_byspringboot.dto.OrderRequest;
import org.example.store_byspringboot.model.OrderItem;
import org.example.store_byspringboot.model.Orders;
import org.example.store_byspringboot.model.Product;
import org.example.store_byspringboot.repository.OrderItemRepository;
import org.example.store_byspringboot.repository.OrderRepository;
import org.example.store_byspringboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    //
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepositpry, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepositpry;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }


    @Transactional
    public Orders createOrder(OrderRequest orderRequest){
        Orders orders = new Orders();

        //Lấy data người đặt từ request
        orders.setStatus("Accepted");
        orders.setCustomerName(orderRequest.getCustomerName());
        orders.setCustomerEmail(orderRequest.getCustomerEmail());

        List<OrderItem> item_toSave = new ArrayList<>();

        //Tính total_amount
        BigDecimal total_amount  = BigDecimal.ZERO;
        for(OrderItemRequest o : orderRequest.getOrderItems()){

            Product product = productRepository.findById(o.getProductId()).orElseThrow(() -> new RuntimeException("Product not found with ID :"+o.getProductId()));

            if (product.getStock() < o.getQuantity()){
                throw new RuntimeException("Not enough available product "+ product.getName());
            }

            BigDecimal temp = product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity()));
            total_amount = total_amount.add(temp);

           //Tính số sản phẩm còn
            int remainQuantity = product.getStock()-o.getQuantity();
            product.setStock(remainQuantity);
            productRepository.save(product);

            //Tạo entity OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(o.getQuantity());
            orderItem.setUnitPrice(product.getPrice());
            orderItem.setProduct(product);
            orderItem.setOrder(orders);

            item_toSave.add(orderItem);

        }
        orders.setTotalAmount(total_amount);
        orders.setOrderItems(item_toSave);
        orderRepository.save(orders);

        return orders;
    }

    //Viết một hàm để lấy ra thông tin chi tiết của một Đơn hàng (dựa vào ID),
    // bao gồm cả danh sách các sản phẩm và số lượng mua trong đơn đó.
    public OrderResponse getOrderbyId(Integer id){
        OrderResponse rs = new OrderResponse();

        Orders order =orderRepository.findById(id).orElse(null);

        rs.setCustomerName(order.getCustomerName());
        rs.setCustomerEmail(order.getCustomerEmail());
        rs.setTotal_amount(order.getTotalAmount());
        rs.setStatus(order.getStatus());

        List<OrderItemResponse> listItemRep = new ArrayList<>();
        for(OrderItem orderItem : order.getOrderItems()){
            OrderItemResponse item = new OrderItemResponse();

            item.setProductId(orderItem.getProduct().getId());
            item.setProductName(orderItem.getProduct().getName());
            item.setQuantity(orderItem.getQuantity());

            listItemRep.add(item);
        }
        rs.setOrderItems(listItemRep);

        return rs;
    }

    @Transactional
    public void cancelOrder(Integer id){
        Orders orders = orderRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Order not found with ID : " +id));
        if (orders.getStatus().equals("Cancelled")){
            throw new RuntimeException("Order Cancelled before");
        }
        for(OrderItem o : orders.getOrderItems()){
            Product product = o.getProduct();
            product.setStock(product.getStock() + o.getQuantity());
            productRepository.save(product);
        }
        orders.setStatus("Cancelled");
        orderRepository.save(orders);
    }

}
