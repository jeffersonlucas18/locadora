package com.test_unity.locadora.service;

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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

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

        Mockito.when(carroEntityRepository.save(Mockito.any())).thenReturn(carroReturn);

        var carroSave = carroService.salvar(carroReturn);
        Assertions.assertNotNull(carroSave);
        Assertions.assertEquals("SEDAN", carro.getModelo());

        Mockito.verify(carroEntityRepository).save(Mockito.any());
    }

    @Test
    @DisplayName("SERVER PARA DAR ERROR AO SALVAR COM A DIARIA NULLA")
    void deveDarErrorAoTentarSalvarCarroComDiariaNulla() {
        CarroEntity carro = new CarroEntity(null, "SEDAN", 0, 2018);
        var error = catchThrowable(()->  carroService.salvar(carro));
        assertThat(error).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(carroEntityRepository, Mockito.never()).save(Mockito.any());
    }

}
