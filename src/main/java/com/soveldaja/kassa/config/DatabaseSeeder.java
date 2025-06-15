package com.soveldaja.kassa.config;

import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.repository.UserRepository;
import com.soveldaja.kassa.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DrinkService drinkService;


    @Override
    public void run(String... args) throws Exception {
        createDrinks();
    }


    private void createDrinks() {
        DrinkDTO newDrink = new DrinkDTO();
        newDrink.setName("New Fashioned");
        newDrink.setPrice(BigDecimal.valueOf(10));

        DrinkDTO oldDrink = new DrinkDTO();
        newDrink.setName("Old Fashioned");
        newDrink.setPrice(BigDecimal.valueOf(15));

        drinkService.saveDrinks(List.of(newDrink, oldDrink));
    }

}
