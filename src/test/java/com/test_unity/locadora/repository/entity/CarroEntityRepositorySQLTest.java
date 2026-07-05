package com.test_unity.locadora.repository.entity;

import com.test_unity.locadora.model.entity.CarroEntity;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CarroEntityRepositorySQLTest {

    @Autowired
    private  CarroEntityRepository carroEntityRepository;

    @Test
    @Sql("/sql/popular-carro.sql")
    void deveBuscarCarroModelo() {
        List<CarroEntity> carroEntityList = carroEntityRepository.findAllByModelo("SUV");
        Assertions.assertEquals(1, carroEntityList.size());
    }

}
