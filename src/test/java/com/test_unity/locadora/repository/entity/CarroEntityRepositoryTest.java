package com.test_unity.locadora.repository.entity;

import com.test_unity.locadora.model.entity.CarroEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroEntityRepositoryTest {

    @Autowired
    private CarroEntityRepository carroEntityRepository;

    @Test
    void deveSalvarUmCarro() {
        CarroEntity carro = new CarroEntity(null, "sedan", 100, 2029);

        carroEntityRepository.save(carro);

        assertNotNull(carro.getId());
    }
}