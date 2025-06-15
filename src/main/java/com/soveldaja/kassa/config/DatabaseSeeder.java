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
        DrinkDTO drink1 = new DrinkDTO();
        drink1.setName("New Fashioned");
        drink1.setPrice(BigDecimal.valueOf(10));

        DrinkDTO drink2 = new DrinkDTO();
        drink2.setName("Old Fashioned");
        drink2.setPrice(BigDecimal.valueOf(15));

        drinkService.saveDrinks(List.of(drink1, drink2));
    }
}
