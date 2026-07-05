package com.test_unity.locadora.repository.entity;

import com.test_unity.locadora.model.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroEntityRepository extends JpaRepository<CarroEntity, Long> {
    List<CarroEntity> findAllByModelo(String hatch);
}
