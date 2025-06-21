package com.soveldaja.kassa.service;


import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.entity.Drink;
import com.soveldaja.kassa.entity.Register;
import com.soveldaja.kassa.repository.DrinkRepository;
import com.soveldaja.kassa.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final RegisterRepository registerRepository;


    public List<DrinkDTO> getAllDrinks() {
        return drinkRepository.findAll().stream()
                .map(DrinkDTO::toDto)
                .collect(Collectors.toList());
    }


    public List<DrinkDTO> getDrinksByRegisterId(Long registerId) {
        return drinkRepository.findByRegisterId(registerId).stream()
                .map(DrinkDTO::toDto)
                .collect(Collectors.toList());
    }


    public DrinkDTO getDrinkById(Long id) {
        return drinkRepository.findById(id)
                .map(DrinkDTO::toDto)
                .orElseThrow(() -> new RuntimeException("Drink not found with id: " + id));
    }


    public void saveDrinks(List<DrinkDTO> drinks) {
        drinkRepository.saveAll(drinks.stream()
                .map(drinkDto -> {
                    Drink drink = Drink.builder()
                            .name(drinkDto.getName())
                            .price(drinkDto.getPrice())
                            .build();

                    if (drinkDto.getRegisterId() != null) {
                        Register register = registerRepository.findById(drinkDto.getRegisterId())
                                .orElseThrow(() -> new RuntimeException("Register not found with id: " + drinkDto.getRegisterId()));
                        drink.setRegister(register);
                    }

                    return drink;
                })
                .toList());
    }


    public void assignDrinkToRegister(Long drinkId, Long registerId) {
        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new RuntimeException("Drink not found with id: " + drinkId));
        Register register = registerRepository.findById(registerId)
                .orElseThrow(() -> new RuntimeException("Register not found with id: " + registerId));

        drink.setRegister(register);
        drinkRepository.save(drink);
    }
}
