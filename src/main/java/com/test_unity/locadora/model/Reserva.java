package com.test_unity.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Reserva {
    private Cliente cliente;
    private Carro carro;
    private int dias;


    public Reserva(Cliente cliente, Carro carro, int dias) {
        if (dias < 1) {
            throw new IllegalArgumentException("Dias invalida");
        }

        this.cliente = cliente;
        this.carro = carro;
        this.dias = dias;
    }

    public double calcularTotal(){
      return  this.carro.calcularValorAluguel(this.dias);
    }
}
