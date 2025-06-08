package com.soveldaja.kassa.controller;

import com.soveldaja.kassa.dto.OrderDTO;
import com.soveldaja.kassa.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String customerId,
            HttpServletRequest request) {

        List<OrderDTO> orders = orderService.getAllOrders(status, customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id, HttpServletRequest request) {
        try {
            OrderDTO order = orderService.getOrderById(Long.parseLong(id));
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Order not found"));
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request) {
        try {
            // Set the creator of the order
            String username = (String) request.getAttribute("username");
            orderDTO.setCreatedBy(username);

            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> statusUpdate,
            HttpServletRequest request) {

        String status = statusUpdate.get("status");
        if (status == null || status.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Status is required"));
        }

        try {
            OrderDTO updatedOrder = orderService.updateOrderStatus(Long.parseLong(id), status);
            return ResponseEntity.ok(Map.of(
                    "id", updatedOrder.getId(),
                    "status", updatedOrder.getStatus()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Order not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id, HttpServletRequest request) {
        // Check if user is admin
        String userRole = (String) request.getAttribute("userRole");
        if (!"admin".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            orderService.deleteOrder(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Order not found"));
        }
    }
}
