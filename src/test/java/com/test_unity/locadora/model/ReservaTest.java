package com.test_unity.locadora.model;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Jose");
        carro = new Carro("Corsa", 50);
    }

    @Test
    void deveCriarUmaReserva() {

        var dias = 5;

        var reserva = new Reserva(cliente, carro, dias);

        Assertions.assertThat(reserva).isNotNull();
    }

    void deveDarErroAoCriarUmaReservaComDiasNegativos() {
    }

    void deveCalcularOTotalDoAluguel() {
    }
}