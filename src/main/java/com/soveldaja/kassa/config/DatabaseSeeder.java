package com.soveldaja.kassa.config;

import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.entity.Register;
import com.soveldaja.kassa.repository.DrinkRepository;
import com.soveldaja.kassa.repository.RegisterRepository;
import com.soveldaja.kassa.repository.UserRepository;
import com.soveldaja.kassa.service.DrinkService;
import com.soveldaja.kassa.service.RegisterService;
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
    private final RegisterService registerService;
    private final DrinkRepository drinkRepository;
    private final RegisterRepository registerRepository;


    @Override
    public void run(String... args) {
        createRegisters();
        createDrinks();
    }


    private void createDrinks() {
        DrinkDTO drink1 = new DrinkDTO();
        drink1.setName("Õlu");
        drink1.setPrice(BigDecimal.valueOf(4));
        drink1.setRegisterId(1L); // Beer register

        DrinkDTO drink2 = new DrinkDTO();
        drink2.setName("Siider");
        drink2.setPrice(BigDecimal.valueOf(5));
        drink2.setRegisterId(1L); // Cocktail register

        DrinkDTO drink3 = new DrinkDTO();
        drink3.setName("Mingi kotkeil 1");
        drink3.setPrice(BigDecimal.valueOf(10));
        drink3.setRegisterId(2L);

        drinkService.saveDrinks(List.of(drink1, drink2, drink3));
    }


    private void createRegisters() {
        Register register1 = new Register();
        register1.setName("Õllekas 1");

        Register register2 = new Register();
        register2.setName("Kokteil 1");

        registerService.saveRegister(register1);
        registerService.saveRegister(register2);
    }
}
