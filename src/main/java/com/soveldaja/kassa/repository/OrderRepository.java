package com.soveldaja.kassa.repository;

import com.soveldaja.kassa.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByRegisterIdOrderByCreatedAtDesc(Integer registerId, Pageable pageable);
}
