package com.soveldaja.kassa.controller;

import com.soveldaja.kassa.entity.Register;
import com.soveldaja.kassa.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;


    @GetMapping
    public ResponseEntity<List<Register>> getAllRegisters() {
        List<Register> registers = registerService.getAllRegisters();
        return ResponseEntity.ok(registers);
    }
}
