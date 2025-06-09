package com.soveldaja.kassa.service;

import com.soveldaja.kassa.dto.OrderDTO;
import com.soveldaja.kassa.dto.OrderItemDTO;
import com.soveldaja.kassa.entity.Customer;
import com.soveldaja.kassa.entity.Order;
import com.soveldaja.kassa.entity.OrderItem;
import com.soveldaja.kassa.repository.CustomerRepository;
import com.soveldaja.kassa.repository.OrderItemRepository;
import com.soveldaja.kassa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    public List<OrderDTO> getAllOrders(String status, String customerId) {
        List<Order> orders = orderRepository.findAll();

        // Filter by status if provided
        if (status != null && !status.isEmpty()) {
            orders = orders.stream()
                    .filter(order -> status.equals(order.getStatus()))
                    .collect(Collectors.toList());
        }

        // Filter by customerId if provided
        if (customerId != null && !customerId.isEmpty()) {
            orders = orders.stream()
                    .filter(order -> order.getCustomer() != null && 
                            customerId.equals(order.getCustomer().getId().toString()))
                    .collect(Collectors.toList());
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
        Customer customer = customerRepository.findById(Long.parseLong(orderDTO.getCustomerId()))
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + orderDTO.getCustomerId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setTotal(orderDTO.getTotal());
        order.setCreatedBy(orderDTO.getCreatedBy());
        order.setStatus(orderDTO.getStatus());
        order.setDescription(orderDTO.getDescription());

        Order savedOrder = orderRepository.save(order);

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

        return convertToDTO(savedOrder);
    }

    @Transactional
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        Customer customer = customerRepository.findById(Long.parseLong(orderDTO.getCustomerId()))
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + orderDTO.getCustomerId()));

        order.setCustomer(customer);
        order.setTotal(orderDTO.getTotal());
        order.setCreatedBy(orderDTO.getCreatedBy());
        order.setStatus(orderDTO.getStatus());
        order.setDescription(orderDTO.getDescription());

        // Remove existing items
        orderItemRepository.deleteAll(order.getItems());
        order.getItems().clear();

        // Add new items
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            orderItems.add(item);
        }

        orderItemRepository.saveAll(orderItems);
        order.setItems(orderItems);

        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return convertToDTO(updatedOrder);
    }

    private OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId().toString(),
                order.getCustomer().getId().toString(),
                itemDTOs,
                order.getTotal(),
                order.getCreatedAt(),
                order.getCreatedBy(),
                order.getStatus(),
                order.getDescription()
        );
    }
}
