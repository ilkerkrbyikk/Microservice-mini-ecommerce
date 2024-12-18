package com.Ilker.order_service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long id;
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private int quantity;

}
