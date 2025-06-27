package com.soveldaja.kassa.config;

import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.entity.Register;
import com.soveldaja.kassa.repository.DrinkRepository;
import com.soveldaja.kassa.repository.RegisterRepository;
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
    private final DrinkRepository drinkRepository;
    private final RegisterRepository registerRepository;


    @Override
    public void run(String... args) {
        createRegisters();
        createBeers();
        createCoctails();
        createShots();
    }


    private void createBeers() {
        /*
            Õlu 6
            Siider 6
            Kali 4
            Longero 6
        */
        DrinkDTO beerDto = new DrinkDTO();
        beerDto.setName("Õlu");
        beerDto.setPrice(BigDecimal.valueOf(6));
        beerDto.setRegisterId(1L);

        DrinkDTO ciderDto = new DrinkDTO();
        ciderDto.setName("Siider");
        ciderDto.setPrice(BigDecimal.valueOf(6));
        ciderDto.setRegisterId(1L);

        DrinkDTO kaliDto = new DrinkDTO();
        kaliDto.setName("Kali");
        kaliDto.setPrice(BigDecimal.valueOf(4));
        kaliDto.setRegisterId(1L);

        DrinkDTO longeroDto = new DrinkDTO();
        longeroDto.setName("Longero");
        longeroDto.setPrice(BigDecimal.valueOf(10));
        longeroDto.setRegisterId(1L);

        drinkService.saveDrinks(List.of(beerDto, ciderDto, longeroDto, kaliDto));
    }


    private void createCoctails() {
        /*
            GT 9
            Greibi Collins 9
            Rumm Cola 9
            Aperitivo Spritz 9
            Limoncello Spritz 9
            Mojito 9
        */

        DrinkDTO gtDto = new DrinkDTO();
        gtDto.setName("GT");
        gtDto.setPrice(BigDecimal.valueOf(9));
        gtDto.setRegisterId(2L);

        DrinkDTO greibiDto = new DrinkDTO();
        greibiDto.setName("Greibi Collins");
        greibiDto.setPrice(BigDecimal.valueOf(9));
        greibiDto.setRegisterId(2L);

        DrinkDTO rummDto = new DrinkDTO();
        rummDto.setName("Rumm Cola");
        rummDto.setPrice(BigDecimal.valueOf(9));
        rummDto.setRegisterId(2L);

        DrinkDTO aperativoDto = new DrinkDTO();
        aperativoDto.setName("Aperitivo Spritz");
        aperativoDto.setPrice(BigDecimal.valueOf(9));
        aperativoDto.setRegisterId(2L);

        DrinkDTO limoncelloDto = new DrinkDTO();
        limoncelloDto.setName("Limoncello Spritz");
        limoncelloDto.setPrice(BigDecimal.valueOf(9));
        limoncelloDto.setRegisterId(2L);

        DrinkDTO mojitoDto = new DrinkDTO();
        mojitoDto.setName("Mojito");
        mojitoDto.setPrice(BigDecimal.valueOf(9));
        mojitoDto.setRegisterId(2L);

        drinkService.saveDrinks(List.of(gtDto, greibiDto, rummDto, aperativoDto, limoncelloDto, mojitoDto));
    }


    private void createShots() {
            /*
            Viin 4
            Tekiila 4
            Limoncello 4
            Shanky's whip 4
            */
        DrinkDTO shotDto = new DrinkDTO();
        shotDto.setName("Shanky's whip");
        shotDto.setPrice(BigDecimal.valueOf(4));
        shotDto.setRegisterId(3L);

        DrinkDTO vodkaDto = new DrinkDTO();
        vodkaDto.setName("Vodka");
        vodkaDto.setPrice(BigDecimal.valueOf(4));
        vodkaDto.setRegisterId(3L);

        DrinkDTO tequilaDto = new DrinkDTO();
        tequilaDto.setName("Tequila");
        tequilaDto.setPrice(BigDecimal.valueOf(4));
        tequilaDto.setRegisterId(3L);

        DrinkDTO limoncelloDto = new DrinkDTO();
        limoncelloDto.setName("Limoncello");
        limoncelloDto.setPrice(BigDecimal.valueOf(4));
        limoncelloDto.setRegisterId(3L);

        drinkService.saveDrinks(List.of(shotDto, vodkaDto, tequilaDto, limoncelloDto));
    }


    private void createRegisters() {
        Register register1 = new Register();
        register1.setName("Õllekas 1");

        Register register2 = new Register();
        register2.setName("Kokteil 1");

        Register register3 = new Register();
        register3.setName("Shot 1");

        registerService.saveRegister(register1);
        registerService.saveRegister(register2);
        registerService.saveRegister(register3);
    }
}
