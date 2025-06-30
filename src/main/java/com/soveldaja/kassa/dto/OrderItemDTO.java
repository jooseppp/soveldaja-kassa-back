package com.soveldaja.kassa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private String drinkId;
    private String drinkName;
    private Integer quantity;

    public OrderItemDTO(String drinkId, Integer quantity) {
        this.drinkId = drinkId;
        this.quantity = quantity;
    }
}
