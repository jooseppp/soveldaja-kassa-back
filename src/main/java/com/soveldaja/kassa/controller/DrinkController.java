package com.soveldaja.kassa.controller;


import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
@RequiredArgsConstructor
public class DrinkController {
    private final DrinkService drinkService;


    @GetMapping
    public ResponseEntity<List<DrinkDTO>> getAllDrinks() {
        return ResponseEntity.ok(drinkService.getAllDrinks());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DrinkDTO> getDrinkById(@PathVariable Long id) {
        return ResponseEntity.ok(drinkService.getDrinkById(id));
    }


    @PostMapping
    public ResponseEntity<?> createDrink(@RequestBody DrinkDTO drinkDTO) {
        drinkService.saveDrinks(List.of(drinkDTO));
        return ResponseEntity.ok().build();
    }
}