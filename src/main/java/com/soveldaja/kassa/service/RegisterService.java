package com.soveldaja.kassa.service;

import com.soveldaja.kassa.entity.Register;
import com.soveldaja.kassa.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final RegisterRepository registerRepository;


    public List<Register> getAllRegisters() {
        return registerRepository.findAll();
    }


    public void saveRegister(Register register) {
        registerRepository.save(register);
    }
}
