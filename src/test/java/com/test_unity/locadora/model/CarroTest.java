package com.test_unity.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel")
    void calcularValorAluguel() {
        Carro carro = new Carro("sedan", 100.0);
        double total = carro.calcularValorAluguel(3);
        Assertions.assertEquals(300.0, total);
    }

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel com desconto")
    void calcularValorAluguelDesconto() {
//      CENARIO
        Carro carro = new Carro("sedan", 100.0);
        int qtdeDias = 5;
//        EXECUCAO
        double total = carro.calcularValorAluguel(qtdeDias);

//        VERIFICACAO
        Assertions.assertEquals(450.0, total);
    }
}
