package com.example.grpckafka.models;

public class ProductEvent {
    private Long id;
    private Long orderId;
    private ProductStatus status;

    public ProductEvent() {}

    public ProductEvent(Long id, Long orderId, ProductStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
