package com.soveldaja.kassa.dto;

import com.soveldaja.kassa.entity.Drink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean isShot;
    private Long registerId;


    public static DrinkDTO toDto(Drink drink) {
        Long registerId = drink.getRegister() != null ? drink.getRegister().getId() : null;
        return new DrinkDTO(drink.getId(), drink.getName(), drink.getPrice(), drink.isShot(), registerId);
    }
}
