package com.soveldaja.kassa.controller;

import com.soveldaja.kassa.dto.ProductDTO;
import com.soveldaja.kassa.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(required = false) String category,
            HttpServletRequest request) {
        
        List<ProductDTO> products = productService.getAllProducts();
        
        // Filter by category if provided
        if (category != null && !category.isEmpty()) {
            products = products.stream()
                    .filter(product -> category.equals(product.getCategory()))
                    .collect(Collectors.toList());
        }
        
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id, HttpServletRequest request) {
        try {
            ProductDTO product = productService.getProductById(Long.parseLong(id));
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Product not found"));
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
        // Check if user is admin
        String userRole = (String) request.getAttribute("userRole");
        if (!"admin".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        try {
            ProductDTO createdProduct = productService.createProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable String id,
            @RequestBody ProductDTO productDTO,
            HttpServletRequest request) {
        
        // Check if user is admin
        String userRole = (String) request.getAttribute("userRole");
        if (!"admin".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        try {
            ProductDTO updatedProduct = productService.updateProduct(Long.parseLong(id), productDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Product not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id, HttpServletRequest request) {
        // Check if user is admin
        String userRole = (String) request.getAttribute("userRole");
        if (!"admin".equals(userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        try {
            productService.deleteProduct(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Product not found"));
        }
    }
}