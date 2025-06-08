package com.soveldaja.kassa.service;


import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.entity.Drink;
import com.soveldaja.kassa.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    private final com.soveldaja.kassa.repository.DrinkRepository drinkRepository;


    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }


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


    private DrinkDTO convertToDTO(Drink drink) {
        return new DrinkDTO(drink.getId(), drink.getName(), drink.getPrice());
    }
}