package com.test_unity.locadora.controller;

import com.test_unity.locadora.model.entity.CarroEntity;
import com.test_unity.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CarroRestController.class)
public class CarroRestController {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CarroService carroService;

    @Test
    void deveSalvaCarro() throws Exception {
//        cenario
        CarroEntity carro = new CarroEntity(1L, "HONDA CIVIC", 150, 2027);
        Mockito.when(carroService.salvar(Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "HONDA CIVIC",
                    "ano": "2028",
                    "valorDiaria": 130,
                    "ano": 2027
                }
                """;

        //        execucao
        ResultActions resultActions = mockMvc.perform(
                    post("/carro/salvar.json")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
        );

//        verificacao
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)) ;
    }
}
