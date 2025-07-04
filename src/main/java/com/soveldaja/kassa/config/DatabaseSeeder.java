package com.soveldaja.kassa.config;

import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.entity.Register;
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

    private final DrinkService drinkService;
    private final RegisterService registerService;


    @Override
    public void run(String... args) {
        createRegisters();
        createBeers();
        createCoctails();
        createShots();
        createPandiButtons();
    }


    private void createBeers() {
        /*
            Õlu 6
            Siider 6
            Kali 4
            Longero 6
        */
        // Create beers for register 7
        DrinkDTO beerDto7 = new DrinkDTO();
        beerDto7.setName("Õlu");
        beerDto7.setPrice(BigDecimal.valueOf(6));
        beerDto7.setRegisterId(7L);

        DrinkDTO ciderDto7 = new DrinkDTO();
        ciderDto7.setName("Siider");
        ciderDto7.setPrice(BigDecimal.valueOf(6));
        ciderDto7.setRegisterId(7L);

        DrinkDTO kaliDto7 = new DrinkDTO();
        kaliDto7.setName("Kali");
        kaliDto7.setPrice(BigDecimal.valueOf(4));
        kaliDto7.setRegisterId(7L);

        DrinkDTO longeroDto7 = new DrinkDTO();
        longeroDto7.setName("Longero");
        longeroDto7.setPrice(BigDecimal.valueOf(6));
        longeroDto7.setRegisterId(7L);

        // Create beers for register 8
        DrinkDTO beerDto8 = new DrinkDTO();
        beerDto8.setName("Õlu");
        beerDto8.setPrice(BigDecimal.valueOf(6));
        beerDto8.setRegisterId(8L);

        DrinkDTO ciderDto8 = new DrinkDTO();
        ciderDto8.setName("Siider");
        ciderDto8.setPrice(BigDecimal.valueOf(6));
        ciderDto8.setRegisterId(8L);

        DrinkDTO kaliDto8 = new DrinkDTO();
        kaliDto8.setName("Kali");
        kaliDto8.setPrice(BigDecimal.valueOf(4));
        kaliDto8.setRegisterId(8L);

        DrinkDTO longeroDto8 = new DrinkDTO();
        longeroDto8.setName("Longero");
        longeroDto8.setPrice(BigDecimal.valueOf(6));
        longeroDto8.setRegisterId(8L);

        // Create beers for register 9
        DrinkDTO beerDto9 = new DrinkDTO();
        beerDto9.setName("Õlu");
        beerDto9.setPrice(BigDecimal.valueOf(6));
        beerDto9.setRegisterId(9L);

        DrinkDTO ciderDto9 = new DrinkDTO();
        ciderDto9.setName("Siider");
        ciderDto9.setPrice(BigDecimal.valueOf(6));
        ciderDto9.setRegisterId(9L);

        DrinkDTO kaliDto9 = new DrinkDTO();
        kaliDto9.setName("Kali");
        kaliDto9.setPrice(BigDecimal.valueOf(4));
        kaliDto9.setRegisterId(9L);

        DrinkDTO longeroDto9 = new DrinkDTO();
        longeroDto9.setName("Longero");
        longeroDto9.setPrice(BigDecimal.valueOf(6));
        longeroDto9.setRegisterId(9L);

        drinkService.saveDrinks(List.of(
                beerDto7, ciderDto7, kaliDto7, longeroDto7,
                beerDto8, ciderDto8, kaliDto8, longeroDto8,
                beerDto9, ciderDto9, kaliDto9, longeroDto9
        ));
    }


    private void createCoctails() {
        /*
            Aperativo Spritz  9
            Limoncello Spritz 9
            GT 9
            Greibi Collins 9
            Cuba libre 9
            Mojito 9
            Cava 7

            Greibilimps 4
            Vesi mullita/mulliga 2
            Coca-cola 3
        */
        // Create a list to hold all cocktails
        List<DrinkDTO> cocktails = new java.util.ArrayList<>();

        // Define the cocktail names and prices
        String[][] cocktailData = {
            {"Aperativo Spritz", "9"},
            {"Limoncello Spritz", "9"},
            {"GT", "9"},
            {"Greibi Collins", "9"},
            {"Cuba libre", "9"},
            {"Mojito", "9"},
            {"Cava", "7"},
            {"Greibilimps", "4"},
            {"Vesi mullita/mulliga", "2"},
            {"Coca-cola", "3"}
        };

        // Create one instance of each cocktail for each register (1-6)
        for (long registerId = 1; registerId <= 6; registerId++) {
            for (String[] cocktailInfo : cocktailData) {
                DrinkDTO drinkDTO = new DrinkDTO();
                drinkDTO.setName(cocktailInfo[0]);
                drinkDTO.setPrice(BigDecimal.valueOf(Double.parseDouble(cocktailInfo[1])));
                drinkDTO.setRegisterId(registerId);
                cocktails.add(drinkDTO);
            }
        }

        drinkService.saveDrinks(cocktails);
    }


    private void createShots() {
            /*
            Viin 4
            Tekiila raposado 4
            Limoncello 4
            Shanky's whip 4
            */
        // Create a list to hold all shots
        List<DrinkDTO> shots = new java.util.ArrayList<>();

        // Define the shot names and prices
        String[][] shotData = {
            {"Shanky's whip", "4"},
            {"Viin", "4"},
            {"Tekiila raposado", "4"},
            {"Limoncello", "4"}
        };

        // Create one instance of each shot for each register (1-6)
        for (long registerId = 1; registerId <= 6; registerId++) {
            for (String[] shotInfo : shotData) {
                DrinkDTO drinkDTO = new DrinkDTO();
                drinkDTO.setName(shotInfo[0]);
                drinkDTO.setPrice(BigDecimal.valueOf(Double.parseDouble(shotInfo[1])));
                drinkDTO.setRegisterId(registerId);
                drinkDTO.setShot(true);
                shots.add(drinkDTO);
            }
        }

        drinkService.saveDrinks(shots);
    }


    private void createRegisters() {
        // Registers 1-6 for cocktails and shots
        Register register1 = new Register();
        register1.setName("Kokteil 1");

        Register register2 = new Register();
        register2.setName("Kokteil 2");

        Register register3 = new Register();
        register3.setName("Kokteil 3");

        Register register4 = new Register();
        register4.setName("Kokteil 4");

        Register register5 = new Register();
        register5.setName("Kokteil 5");

        Register register6 = new Register();
        register6.setName("Kokteil 6");

        // Registers 7-9 for beers
        Register register7 = new Register();
        register7.setName("Õllekas 1");

        Register register8 = new Register();
        register8.setName("Õllekas 2");

        Register register9 = new Register();
        register9.setName("Õllekas 3");

        registerService.saveRegister(register1);
        registerService.saveRegister(register2);
        registerService.saveRegister(register3);
        registerService.saveRegister(register4);
        registerService.saveRegister(register5);
        registerService.saveRegister(register6);
        registerService.saveRegister(register7);
        registerService.saveRegister(register8);
        registerService.saveRegister(register9);
    }


    private void createPandiButtons() {
        List<Register> registers = registerService.getAllRegisters();
        List<DrinkDTO> pandiButtons = new java.util.ArrayList<>();

        for (Register register : registers) {
            DrinkDTO panditopsDto = new DrinkDTO();
            panditopsDto.setName("Panditops");
            panditopsDto.setPrice(BigDecimal.valueOf(2));
            panditopsDto.setRegisterId(register.getId());

            DrinkDTO pandipokaalDto = new DrinkDTO();
            pandipokaalDto.setName("Pandipokaal");
            pandipokaalDto.setPrice(BigDecimal.valueOf(4));
            pandipokaalDto.setRegisterId(register.getId());

            pandiButtons.add(panditopsDto);
            pandiButtons.add(pandipokaalDto);
        }

        drinkService.saveDrinks(pandiButtons);
    }
}
