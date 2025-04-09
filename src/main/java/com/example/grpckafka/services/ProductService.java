package com.example.grpckafka.services;

import com.example.grpckafka.models.Product;
import com.example.grpckafka.repositories.ProductRepository;
import com.example.grpckafka.grpc.ProductRequest;
import com.example.grpckafka.grpc.ProductResponse;
import com.example.grpckafka.grpc.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void getProductById(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        long id = request.getId();
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            ProductResponse response = ProductResponse.newBuilder()
                    .setId(product.getId())
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setAvailableStock(product.getAvailableStock())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Product not found"));
        }
    }
}
