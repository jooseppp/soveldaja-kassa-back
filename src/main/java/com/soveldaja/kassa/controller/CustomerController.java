package com.soveldaja.kassa.controller;

import com.soveldaja.kassa.dto.CustomerDTO;
import com.soveldaja.kassa.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {
    private final CustomerService customerService;


    @Operation(summary = "Get all customers", description = "Retrieves a list of all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of customers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(HttpServletRequest request) {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }


    @Operation(summary = "Get a customer by ID", description = "Returns a customer based on ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(
            @Parameter(description = "ID of the customer to be retrieved") @PathVariable String id,
            HttpServletRequest request) {
        try {
            CustomerDTO customer = customerService.getCustomerById(Long.parseLong(id));
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Customer not found"));
        }
    }


    @Operation(summary = "Create a new customer", description = "Creates a new customer with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createCustomer(
            @Parameter(description = "Customer data to create", required = true)
            @RequestBody CustomerDTO customerDTO,
            HttpServletRequest request) {
        try {
            CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @Operation(summary = "Update a customer", description = "Updates a customer with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(
            @Parameter(description = "ID of the customer to be updated") @PathVariable String id,
            @Parameter(description = "Updated customer data", required = true) @RequestBody CustomerDTO customerDTO,
            HttpServletRequest request) {

        try {
            CustomerDTO updatedCustomer = customerService.updateCustomer(Long.parseLong(id), customerDTO);
            return ResponseEntity.ok(updatedCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Customer not found"));
        }
    }


    @Operation(summary = "Delete a customer", description = "Deletes a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(
            @Parameter(description = "ID of the customer to be deleted") @PathVariable String id,
            HttpServletRequest request) {
        try {
            customerService.deleteCustomer(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Customer not found"));
        }
    }
}
