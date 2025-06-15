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


    public static DrinkDTO toDto(Drink drink) {
        return new DrinkDTO(drink.getId(), drink.getName(), drink.getPrice());
    }
}