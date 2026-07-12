package com.test_unity.locadora.repository.entity;

import com.test_unity.locadora.model.entity.CarroEntity;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class CarroEntityRepositorySQLTest {

    @Autowired
    private CarroEntityRepository carroEntityRepository;

    CarroEntity carro;

    @BeforeEach
    void setUp() {
        carro = new CarroEntity(null, "Honda Civic", 200, 2027);
    }

    @Test
    @Sql("/sql/popular-carro.sql")
    void deveBuscarCarroModelo() {
        List<CarroEntity> carroEntityList = carroEntityRepository.findAllByModelo("SUV");
        Assertions.assertEquals(1, carroEntityList.size());
    }

    @Test
    void deveBuscarCarroPorId() {
        var carroSalvo = carroEntityRepository.save(carro);
        Optional<CarroEntity> carroEntityOptional = carroEntityRepository.findById(carroSalvo.getId());

        assertThat(carroEntityOptional).isPresent();
        assertThat(carroEntityOptional.get().getModelo()).isEqualTo("Honda Civic");
    }

    @Test
    void atualizarCarro() {
        var carroSalvo  = carroEntityRepository.save(carro);
        carroSalvo.setAno(2028);

        var carroAtualizado = carroEntityRepository.save(carroSalvo);

        assertThat(carroAtualizado.getAno()).isEqualTo(2028);
    }


    @Test
    void deveDeletarCarro() {
        var carroSalvo  = carroEntityRepository.save(carro);
        carroEntityRepository.deleteById(carroSalvo.getId());

        Optional<CarroEntity> carroEncontrado = carroEntityRepository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isEmpty();
    }

}
