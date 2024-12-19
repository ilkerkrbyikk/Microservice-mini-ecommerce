package com.Ilker.order_service.service;

import com.Ilker.order_service.client.InventoryClient;
import com.Ilker.order_service.entity.Order;
import com.Ilker.order_service.event.OrderPlacedEvent;
import com.Ilker.order_service.exception.ProductIsNotInStockException;
import com.Ilker.order_service.repository.OrderRepository;
import com.Ilker.order_service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest request){
        var isProductInStock = inventoryClient.isInStock(request.getSkuCode(), request.getQuantity());

        if (isProductInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(request.getPrice());
            order.setQuantity(request.getQuantity());
            order.setSkuCode(request.getSkuCode());

            orderRepository.save(order);

            //TODO: send messages to kafka topic
            /*
            * //TODO: bende userdetails gelmedi keycloackta sıkıntı var
            *    çözüldü.
            * */
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(),request.getUserDetails().getEmail());
            log.info("Start - sending OrderPlacedEvent {} to Kafka topic order-placed.",orderPlacedEvent);
            kafkaTemplate.send("order-placed",orderPlacedEvent);
            log.info("End - sending OrderPlacedEvent {} to Kafka topic order-placed.",orderPlacedEvent);

        }
        else {
            throw new ProductIsNotInStockException("Product with SkuCode " + request.getSkuCode() + " is not in stock.");
        }
    }
}
