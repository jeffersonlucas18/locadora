package com.test_unity.locadora.service;

import com.test_unity.locadora.exception.EntityNotFoundExecption;
import com.test_unity.locadora.model.entity.CarroEntity;
import com.test_unity.locadora.repository.entity.CarroEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroEntityRepository carroEntityRepository;


    public CarroService(CarroEntityRepository carroEntityRepository) {
        this.carroEntityRepository = carroEntityRepository;
    }

    public CarroEntity salvar(CarroEntity carroEntity) {
        if (carroEntity.getValorDiaria() <=0d) {
            throw new IllegalArgumentException("preco da diaria nao pode ser negativo");
        }

        return carroEntityRepository.save(carroEntity);
    }

    public CarroEntity atualizar(Long id, CarroEntity carroEntity) {
        var carro = carroEntityRepository.findById(id).orElseThrow(()->  new EntityNotFoundExecption("Carro nao encontrado"));

        carro.setAno(carroEntity.getAno());
        carro.setModelo(carroEntity.getModelo());
        carro.setValorDiaria(carroEntity.getValorDiaria());
        return carroEntityRepository.save(carro);
    }

    public void deletar(Long id) {
        var carro = carroEntityRepository.findById(id).orElseThrow(()->  new EntityNotFoundExecption("Carro nao encontrado"));

        carroEntityRepository.delete(carro);
    }

    public Optional<CarroEntity> buscarPorId(Long id) {
        return carroEntityRepository.findById(id);
    }

    public List<CarroEntity> carros() {
        return carroEntityRepository.findAll();
    }
}
