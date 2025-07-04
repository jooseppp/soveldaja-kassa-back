package com.soveldaja.kassa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private List<OrderItemDTO> items;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private Integer registerId;
    private boolean isZeroOrder;
}
