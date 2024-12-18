package com.Ilker.order_service.exception;

public class ProductIsNotInStockException extends RuntimeException {
    public ProductIsNotInStockException(String s) {
        super(s);
    }
}
