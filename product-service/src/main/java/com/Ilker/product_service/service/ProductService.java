package com.Ilker.product_service.service;

import com.Ilker.product_service.entity.Product;
import com.Ilker.product_service.repository.ProductRepository;
import com.Ilker.product_service.request.ProductRequest;
import com.Ilker.product_service.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest request){
        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        productRepository.save(product);
        log.info("Product created successfully.");

        return new ProductResponse
                (product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse
                        (product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .collect(Collectors.toList());
    }
}
