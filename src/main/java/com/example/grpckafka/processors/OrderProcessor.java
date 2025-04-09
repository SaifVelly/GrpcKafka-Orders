package com.example.grpckafka.processors;

import com.example.grpckafka.models.OrderEvent;
import com.example.grpckafka.models.ProductEvent;
import com.example.grpckafka.models.ProductStatus;
import com.example.grpckafka.models.Product;
import com.example.grpckafka.repositories.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Function;

@Configuration
public class OrderProcessor {

    private final ProductRepository productRepository;

    public OrderProcessor(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public Function<OrderEvent, ProductEvent> processor() {
        return order -> {
            System.out.println(order);
            Optional<Product> productOptional = productRepository.findById(order.getProductId());

            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                if (product.getAvailableStock() < order.getQuantity()) {
                    return new ProductEvent(product.getId(), order.getId(), ProductStatus.OUT_OF_STOCK);
                } else {
                    product.setAvailableStock(product.getAvailableStock() - order.getQuantity());
                    productRepository.save(product);
                    return new ProductEvent(product.getId(), order.getId(), ProductStatus.AVAILABLE);
                }
            } else {
                return null;
            }
        };
    }
}
