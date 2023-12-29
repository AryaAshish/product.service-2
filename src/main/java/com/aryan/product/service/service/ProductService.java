package com.aryan.product.service.service;

import com.aryan.product.service.dto.ProductRequest;
import com.aryan.product.service.dto.ProductResponse;
import com.aryan.product.service.model.Product;
import com.aryan.product.service.repository.ProductRepository;
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

    public  void createProduct(ProductRequest productRequest){
        Product product = Product.builder().name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();
        log.info("product"+ product.getID() + " is saved");
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
     List<Product> products = productRepository.findAll();
    return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .ID(product.getID())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();

    }
}
