package com.test_unity.locadora.controller;

import com.test_unity.locadora.model.entity.CarroEntity;
import com.test_unity.locadora.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carro")
public class CarroRestController {

    @Autowired
    private CarroService carroService;

    @PostMapping("/salvar.json")
    public ResponseEntity<Object> save(@RequestBody CarroEntity carro) {
        try {
            var carroSalvo = carroService.salvar(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }
}
