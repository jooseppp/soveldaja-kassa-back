package com.soveldaja.kassa.service;

import com.soveldaja.kassa.dto.DrinkDTO;
import com.soveldaja.kassa.dto.OrderDTO;
import com.soveldaja.kassa.dto.OrderItemDTO;
import com.soveldaja.kassa.entity.Order;
import com.soveldaja.kassa.entity.OrderItem;
import com.soveldaja.kassa.repository.OrderItemRepository;
import com.soveldaja.kassa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DrinkService drinkService;


    public List<OrderDTO> getAllOrders(String status, String customerId) {
        // Validate parameters if they are used in the future
        List<Order> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            return new ArrayList<>();
        }

        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }


    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        Order order = new Order();
        order.setRegisterId(orderDTO.getRegisterId());
        order.setItems(new ArrayList<>());

        if (orderDTO.isZeroOrder()) {
            order.setTotal(BigDecimal.valueOf(0));
        } else {
            order.setTotal(orderDTO.getTotal() != null ? orderDTO.getTotal() : BigDecimal.ZERO);
        }

        Order savedOrder = orderRepository.save(order);
        createOrderItems(orderDTO, savedOrder);

        /* Calculate total based on drink prices from database
        BigDecimal total;
        if (orderDTO.isZeroOrder()) {
            total = BigDecimal.valueOf(0);
        } else {
            total = calculateOrderTotal(savedOrder);
        }
        savedOrder.setTotal(total);*/

        // Save again to ensure all changes are persisted
        savedOrder = orderRepository.save(savedOrder);
        return convertToDTO(savedOrder);
    }


    private void createOrderItems(OrderDTO orderDTO, Order savedOrder) {
        if (savedOrder == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        // Ensure items collection is initialized
        if (savedOrder.getItems() == null) {
            savedOrder.setItems(new ArrayList<>());
        }

        // If no items in DTO, just return (items were already cleared in updateOrder)
        if (orderDTO == null || orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
            return;
        }

        List<OrderItem> newItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            if (itemDTO == null) {
                continue;
            }

            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setDrinkId(itemDTO.getDrinkId());
            item.setQuantity(itemDTO.getQuantity() != null ? itemDTO.getQuantity() : 0);
            newItems.add(item);
        }

        // Save all new items
        orderItemRepository.saveAll(newItems);

        // Add all new items to the order's collection
        savedOrder.getItems().addAll(newItems);
    }


    @Transactional
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        // Update registerId if provided
        if (orderDTO.getRegisterId() != null) {
            order.setRegisterId(orderDTO.getRegisterId());
        }

        // Update items - first save the order to ensure it exists in the database
        Order savedOrder = orderRepository.save(order);

        // Then clear and update items
        if (savedOrder.getItems() != null) {
            savedOrder.getItems().clear();
            orderRepository.save(savedOrder); // Save after clearing to ensure changes are persisted
        }

        // Add new items
        createOrderItems(orderDTO, savedOrder);

        // Calculate total based on drink prices from database
        BigDecimal total = calculateOrderTotal(savedOrder);
        savedOrder.setTotal(total);

        // Final save and return
        Order updatedOrder = orderRepository.save(savedOrder);
        return convertToDTO(updatedOrder);
    }


    public void deleteOrder(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        // Check if order exists before deleting
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }

        orderRepository.deleteById(id);
    }


    public List<OrderDTO> getLastOrdersByRegisterId(Integer registerId, int limit) {
        if (registerId == null) {
            throw new IllegalArgumentException("Register ID cannot be null or empty");
        }

        List<Order> orders = orderRepository.findByRegisterIdOrderByCreatedAtDesc(registerId, PageRequest.of(0, limit));

        if (orders.isEmpty()) {
            return new ArrayList<>();
        }

        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    /**
     * Calculates the total price of an order based on the prices of drinks in the database.
     *
     * @param order The order to calculate the total for
     * @return The total price of the order
     */
    private BigDecimal calculateOrderTotal(Order order) {
        if (order == null || order.getItems() == null || order.getItems().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return order.getItems().stream()
                .filter(Objects::nonNull)
                .map(item -> {
                    if (item.getDrinkId() == null || item.getQuantity() == null) {
                        return BigDecimal.ZERO;
                    }

                    try {
                        Long drinkId = Long.parseLong(item.getDrinkId());
                        DrinkDTO drink = drinkService.getDrinkById(drinkId);
                        BigDecimal price = drink.getPrice() != null ? drink.getPrice() : BigDecimal.ZERO;
                        return price.multiply(BigDecimal.valueOf(item.getQuantity()));
                    } catch (Exception e) {
                        // If there's any error (parsing, drink not found, etc.), return zero
                        return BigDecimal.ZERO;
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private OrderDTO convertToDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId() != null ? order.getId().toString() : null);
        dto.setTotal(order.getTotal());
        dto.setRegisterId(order.getRegisterId());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemDTO> itemDTOs = new ArrayList<>();
        if (order.getItems() != null) {
            itemDTOs = order.getItems().stream()
                    .filter(Objects::nonNull)
                    .map(item -> {
                        String drinkId = item.getDrinkId() != null ? item.getDrinkId() : null;
                        Integer quantity = item.getQuantity() != null ? item.getQuantity() : 0;

                        OrderItemDTO orderItemDTO = new OrderItemDTO(drinkId, null, quantity);

                        // Try to get the drink name if drinkId is not null
                        if (drinkId != null) {
                            try {
                                Long drinkIdLong = Long.parseLong(drinkId);
                                DrinkDTO drinkDTO = drinkService.getDrinkById(drinkIdLong);
                                orderItemDTO.setDrinkName(drinkDTO.getName());
                            } catch (Exception e) {
                                // If there's any error (parsing, drink not found, etc.), leave drinkName as null
                            }
                        }

                        return orderItemDTO;
                    })
                    .toList();
        }
        dto.setItems(itemDTOs);
        return dto;
    }
}
