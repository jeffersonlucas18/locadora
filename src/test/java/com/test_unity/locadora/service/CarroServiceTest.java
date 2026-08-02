package com.test_unity.locadora.service;

import com.test_unity.locadora.exception.EntityNotFoundExecption;
import com.test_unity.locadora.model.entity.CarroEntity;
import com.test_unity.locadora.repository.entity.CarroEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarroServiceTest {

    @InjectMocks
    CarroService carroService;

    @Mock
    CarroEntityRepository carroEntityRepository;

    @Test
    void deveSalvarCarro() {
        CarroEntity carro = new CarroEntity(null, "SEDAN", 10, 2018);


        CarroEntity carroReturn = new CarroEntity(null, "SEDAN", 10, 2018);
        carro.setId(1L);

        when(carroEntityRepository.save(Mockito.any())).thenReturn(carroReturn);

        var carroSave = carroService.salvar(carroReturn);
        Assertions.assertNotNull(carroSave);
        Assertions.assertEquals("SEDAN", carro.getModelo());

        Mockito.verify(carroEntityRepository).save(Mockito.any());
    }

    @Test
    @DisplayName("SERVER PARA DAR ERROR AO SALVAR COM A DIARIA NULLA")
    void deveDarErrorAoTentarSalvarCarroComDiariaNulla() {
        CarroEntity carro = new CarroEntity(null, "SEDAN", 0, 2018);
        var error = catchThrowable(() -> carroService.salvar(carro));
        assertThat(error).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(carroEntityRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveAtualizar() {
        var carroExistente = new CarroEntity(1L, "GOL", 10, 2018);
        when(carroEntityRepository.findById(1L)).thenReturn(Optional.of(carroExistente));

            var carroAtualizado = new CarroEntity(1L, "GOL", 10, 2018);
            when(carroEntityRepository.save(Mockito.any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new CarroEntity(1L, "SEDAN", 10, 2018);
        var result = carroService.atualizar(id, carro);

        Assertions.assertEquals("GOL", result.getModelo());
        Mockito.verify(carroEntityRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deveDarErroAoAtualizar() {
      Long id =1L;
      var carro = new CarroEntity(null, "SEDAN", 10, 2018);

      when(carroEntityRepository.findById(Mockito.any())).thenReturn(Optional.empty());

      var error = catchThrowable(() -> carroService.atualizar(id, carro));
      assertThat(error).isInstanceOf(IllegalArgumentException.class);
      Mockito.verify(carroEntityRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveDarErroAoDeletarCarroNaoExiste() {
        Long id =1L;
        var carro = new CarroEntity(null, "SEDAN", 10, 2018);

        when(carroEntityRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var error = catchThrowable(() -> carroService.deletar(id));
        assertThat(error).isInstanceOf(EntityNotFoundExecption.class);
        Mockito.verify(carroEntityRepository, Mockito.never()).delete(Mockito.any());
    }

    @Test
    void deveDeletarComSucesso() {
        Long id =1L;
        var carro = new CarroEntity(null, "SEDAN", 10, 2018);
        when(carroEntityRepository.findById(Mockito.any())).thenReturn(Optional.of(carro));
        carroService.deletar(id);

        Mockito.verify(carroEntityRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void deveBuscarCarroPorId() {
        Long id =1L;
        var carro = new CarroEntity(null, "SEDAN", 10, 2018);
        when(carroEntityRepository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        var carroEncontrado = carroService.buscarPorId(id);
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("SEDAN");
    }

    @Test
    void deveListarTodosCarros() {
        Long id =1L;
        var carro = new CarroEntity(1L, "SEDAN", 10, 2018);
        var lista = List.of(carro);
        when(carroEntityRepository.findAll()).thenReturn(lista);
        var listaTodos = carroService.carros();
        assertThat(listaTodos).isEqualTo(lista);
    }

}
