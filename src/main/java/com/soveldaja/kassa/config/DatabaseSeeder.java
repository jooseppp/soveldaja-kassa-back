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
        // Create cocktails for register 1
        DrinkDTO aperativoDto1 = new DrinkDTO();
        aperativoDto1.setName("Aperativo Spritz");
        aperativoDto1.setPrice(BigDecimal.valueOf(9));
        aperativoDto1.setRegisterId(1L);

        DrinkDTO limoncelloSpritzDto1 = new DrinkDTO();
        limoncelloSpritzDto1.setName("Limoncello Spritz");
        limoncelloSpritzDto1.setPrice(BigDecimal.valueOf(9));
        limoncelloSpritzDto1.setRegisterId(1L);

        DrinkDTO gtDto1 = new DrinkDTO();
        gtDto1.setName("GT");
        gtDto1.setPrice(BigDecimal.valueOf(9));
        gtDto1.setRegisterId(1L);

        DrinkDTO greibiDto1 = new DrinkDTO();
        greibiDto1.setName("Greibi Collins");
        greibiDto1.setPrice(BigDecimal.valueOf(9));
        greibiDto1.setRegisterId(1L);

        DrinkDTO cubaLibreDto1 = new DrinkDTO();
        cubaLibreDto1.setName("Cuba libre");
        cubaLibreDto1.setPrice(BigDecimal.valueOf(9));
        cubaLibreDto1.setRegisterId(1L);

        DrinkDTO mojitoDto1 = new DrinkDTO();
        mojitoDto1.setName("Mojito");
        mojitoDto1.setPrice(BigDecimal.valueOf(9));
        mojitoDto1.setRegisterId(1L);

        DrinkDTO cavaDto1 = new DrinkDTO();
        cavaDto1.setName("Cava");
        cavaDto1.setPrice(BigDecimal.valueOf(7));
        cavaDto1.setRegisterId(1L);

        DrinkDTO greibilimpsDto1 = new DrinkDTO();
        greibilimpsDto1.setName("Greibilimps");
        greibilimpsDto1.setPrice(BigDecimal.valueOf(4));
        greibilimpsDto1.setRegisterId(1L);

        DrinkDTO vesiDto1 = new DrinkDTO();
        vesiDto1.setName("Vesi mullita/mulliga");
        vesiDto1.setPrice(BigDecimal.valueOf(2));
        vesiDto1.setRegisterId(1L);

        DrinkDTO colaDto1 = new DrinkDTO();
        colaDto1.setName("Coca-cola");
        colaDto1.setPrice(BigDecimal.valueOf(3));
        colaDto1.setRegisterId(1L);

        // Create cocktails for register 2
        DrinkDTO aperativoDto2 = new DrinkDTO();
        aperativoDto2.setName("Aperativo Spritz");
        aperativoDto2.setPrice(BigDecimal.valueOf(9));
        aperativoDto2.setRegisterId(2L);

        DrinkDTO limoncelloSpritzDto2 = new DrinkDTO();
        limoncelloSpritzDto2.setName("Limoncello Spritz");
        limoncelloSpritzDto2.setPrice(BigDecimal.valueOf(9));
        limoncelloSpritzDto2.setRegisterId(2L);

        DrinkDTO gtDto2 = new DrinkDTO();
        gtDto2.setName("GT");
        gtDto2.setPrice(BigDecimal.valueOf(9));
        gtDto2.setRegisterId(2L);

        DrinkDTO greibiDto2 = new DrinkDTO();
        greibiDto2.setName("Greibi Collins");
        greibiDto2.setPrice(BigDecimal.valueOf(9));
        greibiDto2.setRegisterId(2L);

        DrinkDTO cubaLibreDto2 = new DrinkDTO();
        cubaLibreDto2.setName("Cuba libre");
        cubaLibreDto2.setPrice(BigDecimal.valueOf(9));
        cubaLibreDto2.setRegisterId(2L);

        DrinkDTO mojitoDto2 = new DrinkDTO();
        mojitoDto2.setName("Mojito");
        mojitoDto2.setPrice(BigDecimal.valueOf(9));
        mojitoDto2.setRegisterId(2L);

        DrinkDTO cavaDto2 = new DrinkDTO();
        cavaDto2.setName("Cava");
        cavaDto2.setPrice(BigDecimal.valueOf(7));
        cavaDto2.setRegisterId(2L);

        DrinkDTO greibilimpsDto2 = new DrinkDTO();
        greibilimpsDto2.setName("Greibilimps");
        greibilimpsDto2.setPrice(BigDecimal.valueOf(4));
        greibilimpsDto2.setRegisterId(2L);

        DrinkDTO vesiDto2 = new DrinkDTO();
        vesiDto2.setName("Vesi mullita/mulliga");
        vesiDto2.setPrice(BigDecimal.valueOf(2));
        vesiDto2.setRegisterId(2L);

        DrinkDTO colaDto2 = new DrinkDTO();
        colaDto2.setName("Coca-cola");
        colaDto2.setPrice(BigDecimal.valueOf(3));
        colaDto2.setRegisterId(2L);

        // Create cocktails for register 3
        DrinkDTO aperativoDto3 = new DrinkDTO();
        aperativoDto3.setName("Aperativo Spritz");
        aperativoDto3.setPrice(BigDecimal.valueOf(9));
        aperativoDto3.setRegisterId(3L);

        DrinkDTO limoncelloSpritzDto3 = new DrinkDTO();
        limoncelloSpritzDto3.setName("Limoncello Spritz");
        limoncelloSpritzDto3.setPrice(BigDecimal.valueOf(9));
        limoncelloSpritzDto3.setRegisterId(3L);

        DrinkDTO gtDto3 = new DrinkDTO();
        gtDto3.setName("GT");
        gtDto3.setPrice(BigDecimal.valueOf(9));
        gtDto3.setRegisterId(3L);

        DrinkDTO greibiDto3 = new DrinkDTO();
        greibiDto3.setName("Greibi Collins");
        greibiDto3.setPrice(BigDecimal.valueOf(9));
        greibiDto3.setRegisterId(3L);

        DrinkDTO cubaLibreDto3 = new DrinkDTO();
        cubaLibreDto3.setName("Cuba libre");
        cubaLibreDto3.setPrice(BigDecimal.valueOf(9));
        cubaLibreDto3.setRegisterId(3L);

        DrinkDTO mojitoDto3 = new DrinkDTO();
        mojitoDto3.setName("Mojito");
        mojitoDto3.setPrice(BigDecimal.valueOf(9));
        mojitoDto3.setRegisterId(3L);

        DrinkDTO cavaDto3 = new DrinkDTO();
        cavaDto3.setName("Cava");
        cavaDto3.setPrice(BigDecimal.valueOf(7));
        cavaDto3.setRegisterId(3L);

        DrinkDTO greibilimpsDto3 = new DrinkDTO();
        greibilimpsDto3.setName("Greibilimps");
        greibilimpsDto3.setPrice(BigDecimal.valueOf(4));
        greibilimpsDto3.setRegisterId(3L);

        DrinkDTO vesiDto3 = new DrinkDTO();
        vesiDto3.setName("Vesi mullita/mulliga");
        vesiDto3.setPrice(BigDecimal.valueOf(2));
        vesiDto3.setRegisterId(3L);

        DrinkDTO colaDto3 = new DrinkDTO();
        colaDto3.setName("Coca-cola");
        colaDto3.setPrice(BigDecimal.valueOf(3));
        colaDto3.setRegisterId(3L);

        drinkService.saveDrinks(List.of(
            aperativoDto1, limoncelloSpritzDto1, gtDto1, greibiDto1, cubaLibreDto1, mojitoDto1, cavaDto1, greibilimpsDto1, vesiDto1, colaDto1,
            aperativoDto2, limoncelloSpritzDto2, gtDto2, greibiDto2, cubaLibreDto2, mojitoDto2, cavaDto2, greibilimpsDto2, vesiDto2, colaDto2,
            aperativoDto3, limoncelloSpritzDto3, gtDto3, greibiDto3, cubaLibreDto3, mojitoDto3, cavaDto3, greibilimpsDto3, vesiDto3, colaDto3
        ));
    }


    private void createShots() {
            /*
            Viin 4
            Tekiila raposado 4
            Limoncello 4
            Shanky's whip 4
            */
        // Create shots for register 1
        DrinkDTO shankysDto1 = new DrinkDTO();
        shankysDto1.setName("Shanky's whip");
        shankysDto1.setPrice(BigDecimal.valueOf(4));
        shankysDto1.setRegisterId(1L);
        shankysDto1.setShot(true);

        DrinkDTO vodkaDto1 = new DrinkDTO();
        vodkaDto1.setName("Viin");
        vodkaDto1.setPrice(BigDecimal.valueOf(4));
        vodkaDto1.setRegisterId(1L);
        vodkaDto1.setShot(true);

        DrinkDTO tequilaDto1 = new DrinkDTO();
        tequilaDto1.setName("Tekiila raposado");
        tequilaDto1.setPrice(BigDecimal.valueOf(4));
        tequilaDto1.setRegisterId(1L);
        tequilaDto1.setShot(true);

        DrinkDTO limoncelloDto1 = new DrinkDTO();
        limoncelloDto1.setName("Limoncello");
        limoncelloDto1.setPrice(BigDecimal.valueOf(4));
        limoncelloDto1.setRegisterId(1L);
        limoncelloDto1.setShot(true);

        // Create shots for register 2
        DrinkDTO shankysDto2 = new DrinkDTO();
        shankysDto2.setName("Shanky's whip");
        shankysDto2.setPrice(BigDecimal.valueOf(4));
        shankysDto2.setRegisterId(2L);
        shankysDto2.setShot(true);

        DrinkDTO vodkaDto2 = new DrinkDTO();
        vodkaDto2.setName("Viin");
        vodkaDto2.setPrice(BigDecimal.valueOf(4));
        vodkaDto2.setRegisterId(2L);
        vodkaDto2.setShot(true);

        DrinkDTO tequilaDto2 = new DrinkDTO();
        tequilaDto2.setName("Tekiila raposado");
        tequilaDto2.setPrice(BigDecimal.valueOf(4));
        tequilaDto2.setRegisterId(2L);
        tequilaDto2.setShot(true);

        DrinkDTO limoncelloDto2 = new DrinkDTO();
        limoncelloDto2.setName("Limoncello");
        limoncelloDto2.setPrice(BigDecimal.valueOf(4));
        limoncelloDto2.setRegisterId(2L);
        limoncelloDto2.setShot(true);

        // Create shots for register 3
        DrinkDTO shankysDto3 = new DrinkDTO();
        shankysDto3.setName("Shanky's whip");
        shankysDto3.setPrice(BigDecimal.valueOf(4));
        shankysDto3.setRegisterId(3L);
        shankysDto3.setShot(true);

        DrinkDTO vodkaDto3 = new DrinkDTO();
        vodkaDto3.setName("Viin");
        vodkaDto3.setPrice(BigDecimal.valueOf(4));
        vodkaDto3.setRegisterId(3L);
        vodkaDto3.setShot(true);

        DrinkDTO tequilaDto3 = new DrinkDTO();
        tequilaDto3.setName("Tekiila raposado");
        tequilaDto3.setPrice(BigDecimal.valueOf(4));
        tequilaDto3.setRegisterId(3L);
        tequilaDto3.setShot(true);

        DrinkDTO limoncelloDto3 = new DrinkDTO();
        limoncelloDto3.setName("Limoncello");
        limoncelloDto3.setPrice(BigDecimal.valueOf(4));
        limoncelloDto3.setRegisterId(3L);
        limoncelloDto3.setShot(true);

        // Create shots for register 4
        DrinkDTO shankysDto4 = new DrinkDTO();
        shankysDto4.setName("Shanky's whip");
        shankysDto4.setPrice(BigDecimal.valueOf(4));
        shankysDto4.setRegisterId(4L);
        shankysDto4.setShot(true);

        DrinkDTO vodkaDto4 = new DrinkDTO();
        vodkaDto4.setName("Viin");
        vodkaDto4.setPrice(BigDecimal.valueOf(4));
        vodkaDto4.setRegisterId(4L);
        vodkaDto4.setShot(true);

        DrinkDTO tequilaDto4 = new DrinkDTO();
        tequilaDto4.setName("Tekiila raposado");
        tequilaDto4.setPrice(BigDecimal.valueOf(4));
        tequilaDto4.setRegisterId(4L);
        tequilaDto4.setShot(true);

        DrinkDTO limoncelloDto4 = new DrinkDTO();
        limoncelloDto4.setName("Limoncello");
        limoncelloDto4.setPrice(BigDecimal.valueOf(4));
        limoncelloDto4.setRegisterId(4L);
        limoncelloDto4.setShot(true);

        // Create shots for register 5
        DrinkDTO shankysDto5 = new DrinkDTO();
        shankysDto5.setName("Shanky's whip");
        shankysDto5.setPrice(BigDecimal.valueOf(4));
        shankysDto5.setRegisterId(5L);
        shankysDto5.setShot(true);

        DrinkDTO vodkaDto5 = new DrinkDTO();
        vodkaDto5.setName("Viin");
        vodkaDto5.setPrice(BigDecimal.valueOf(4));
        vodkaDto5.setRegisterId(5L);
        vodkaDto5.setShot(true);

        DrinkDTO tequilaDto5 = new DrinkDTO();
        tequilaDto5.setName("Tekiila raposado");
        tequilaDto5.setPrice(BigDecimal.valueOf(4));
        tequilaDto5.setRegisterId(5L);
        tequilaDto5.setShot(true);

        DrinkDTO limoncelloDto5 = new DrinkDTO();
        limoncelloDto5.setName("Limoncello");
        limoncelloDto5.setPrice(BigDecimal.valueOf(4));
        limoncelloDto5.setRegisterId(5L);
        limoncelloDto5.setShot(true);

        // Create shots for register 6
        DrinkDTO shankysDto6 = new DrinkDTO();
        shankysDto6.setName("Shanky's whip");
        shankysDto6.setPrice(BigDecimal.valueOf(4));
        shankysDto6.setRegisterId(6L);
        shankysDto6.setShot(true);

        DrinkDTO vodkaDto6 = new DrinkDTO();
        vodkaDto6.setName("Viin");
        vodkaDto6.setPrice(BigDecimal.valueOf(4));
        vodkaDto6.setRegisterId(6L);
        vodkaDto6.setShot(true);

        DrinkDTO tequilaDto6 = new DrinkDTO();
        tequilaDto6.setName("Tekiila raposado");
        tequilaDto6.setPrice(BigDecimal.valueOf(4));
        tequilaDto6.setRegisterId(6L);
        tequilaDto6.setShot(true);

        DrinkDTO limoncelloDto6 = new DrinkDTO();
        limoncelloDto6.setName("Limoncello");
        limoncelloDto6.setPrice(BigDecimal.valueOf(4));
        limoncelloDto6.setRegisterId(6L);
        limoncelloDto6.setShot(true);

        drinkService.saveDrinks(List.of(
            shankysDto1, vodkaDto1, tequilaDto1, limoncelloDto1,
            shankysDto2, vodkaDto2, tequilaDto2, limoncelloDto2,
            shankysDto3, vodkaDto3, tequilaDto3, limoncelloDto3,
            shankysDto4, vodkaDto4, tequilaDto4, limoncelloDto4,
            shankysDto5, vodkaDto5, tequilaDto5, limoncelloDto5,
            shankysDto6, vodkaDto6, tequilaDto6, limoncelloDto6
        ));
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
