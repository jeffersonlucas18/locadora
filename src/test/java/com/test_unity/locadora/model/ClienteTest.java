package com.test_unity.locadora.model;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    void deveCriarComNome() {

        Cliente cliente = new Cliente("Jefferson");

        String nome = cliente.getNome();

        Assertions.assertNotNull(nome);
        Assertions.assertTrue(nome.startsWith("J"));
        Assertions.assertFalse(nome.length() == 100);

        assertThat(nome).isLessThan("maria5");
        assertThat(nome).isEqualTo("Jefferson");
    }


    @Test
    void deveCriarSemNome() {
        Cliente cliente = new Cliente(null);

        String nome = cliente.getNome();

        Assertions.assertNull(nome);
//        Assertions.assertTrue(nome.startsWith("J"));
//        Assertions.assertFalse(nome.length() == 100);
    }
}
