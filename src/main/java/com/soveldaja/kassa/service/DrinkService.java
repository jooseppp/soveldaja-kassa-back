package com.soveldaja.kassa.service;


import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.entity.Drink;
import com.soveldaja.kassa.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkRepository drinkRepository;


    public List<DrinkDTO> getAllDrinks() {
        return drinkRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public DrinkDTO getDrinkById(Long id) {
        return drinkRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Drink not found with id: " + id));
    }


    public void saveDrinks(List<DrinkDTO> drinks) {
        drinkRepository.saveAll(drinks.stream()
                .map(drinkDto -> Drink.builder()
                        .name(drinkDto.getName())
                        .price(drinkDto.getPrice())
                        .build())
                .toList());
    }


    private DrinkDTO convertToDTO(Drink drink) {
        return new DrinkDTO(drink.getId(), drink.getName(), drink.getPrice());
    }
}