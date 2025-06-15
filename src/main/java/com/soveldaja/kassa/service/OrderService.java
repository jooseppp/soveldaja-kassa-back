package com.soveldaja.kassa.service;

import com.soveldaja.kassa.dto.OrderDTO;
import com.soveldaja.kassa.dto.OrderItemDTO;
import com.soveldaja.kassa.entity.Order;
import com.soveldaja.kassa.entity.OrderItem;
import com.soveldaja.kassa.repository.OrderItemRepository;
import com.soveldaja.kassa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    public List<OrderDTO> getAllOrders(String status, String customerId) {
        List<Order> orders = orderRepository.findAll();

        if (status != null && !status.isEmpty()) {
            orders = orders.stream()
                    .filter(order -> status.equals(order.getStatus()))
                    .toList();
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
        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setCreatedBy(orderDTO.getCreatedBy());
        order.setStatus(orderDTO.getStatus());
        order.setDescription(orderDTO.getDescription());

        Order savedOrder = orderRepository.save(order);
        createOrderItems(orderDTO, savedOrder);

        return convertToDTO(savedOrder);
    }


    private void createOrderItems(OrderDTO orderDTO, Order savedOrder) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            orderItems.add(item);
        }

        orderItemRepository.saveAll(orderItems);
        savedOrder.setItems(orderItems);
    }


    @Transactional
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setTotal(orderDTO.getTotal());
        order.setCreatedBy(orderDTO.getCreatedBy());
        order.setStatus(orderDTO.getStatus());
        order.setDescription(orderDTO.getDescription());

        // Remove existing items
        orderItemRepository.deleteAll(order.getItems());
        order.getItems().clear();

        // Add new items
        createOrderItems(orderDTO, order);

        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }


    private OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId().toString(),
                itemDTOs,
                order.getTotal(),
                order.getCreatedAt(),
                order.getCreatedBy(),
                order.getStatus(),
                order.getDescription()
        );
    }
}
