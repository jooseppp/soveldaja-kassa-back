package com.soveldaja.kassa.repository;

import com.soveldaja.kassa.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> findByName(String name);

    @Query("SELECT d FROM Drink d WHERE d.register.id = :registerId")
    List<Drink> findByRegisterId(Long registerId);
}
