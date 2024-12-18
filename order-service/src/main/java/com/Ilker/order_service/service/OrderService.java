package com.Ilker.order_service.service;

import com.Ilker.order_service.client.InventoryClient;
import com.Ilker.order_service.entity.Order;
import com.Ilker.order_service.exception.ProductIsNotInStockException;
import com.Ilker.order_service.repository.OrderRepository;
import com.Ilker.order_service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest request){
        var isProductInStock = inventoryClient.isInStock(request.getSkuCode(), request.getQuantity());

        if (isProductInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(request.getPrice());
            order.setQuantity(request.getQuantity());
            order.setSkuCode(request.getSkuCode());

            orderRepository.save(order);
        }
        else {
            throw new ProductIsNotInStockException("Product with SkuCode " + request.getSkuCode() + " is not in stock.");
        }
    }
}
