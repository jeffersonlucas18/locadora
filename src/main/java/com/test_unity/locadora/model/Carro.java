package com.test_unity.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carro {
    private String modelo;
    private double valorDiaria;

    public double calcularValorAluguel(int dias) {
        double desconto = 0;
        if (dias >= 5) {
            desconto = 50.0;
        }
        return (dias * valorDiaria) - desconto;
    }

}
