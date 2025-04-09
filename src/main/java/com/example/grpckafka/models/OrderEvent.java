package com.example.grpckafka.models;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrderEvent {
    private Long id;
    private Long productId;
    private int quantity;
}
