package com.springacademy.ecartmicroservicesapp.Dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@RequiredArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;


    public OrderItemDto(Long id, Long productId, Integer quantity, BigDecimal price, BigDecimal subTotal) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }


}
