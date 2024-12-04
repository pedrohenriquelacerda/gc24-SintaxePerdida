package com.caldeira.projetofinal.zelda.controllers;

import static org.junit.jupiter.api.Assertions.*;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZeldaControllerTest {

    @InjectMocks
    ZeldaController MockZeldaController;
    @Mock
    private ZeldaGatewayService MockZeldaGatewayService;

    @InjectMocks
    private ZeldaController zeldaController;

    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Preparando um GameModel mockado para os testes
        gameModel = new GameModel();
        gameModel.setId(UUID.randomUUID().toString()); // Atribui um UUID único ao jogo
        gameModel.setName("Zelda"); // Atribui o nome do jogo
        gameModel.setDescription("An adventurous fantasy game."); // Atribui uma descrição do jogo
        gameModel.setDeveloper("Nintendo"); // Atribui o nome do desenvolvedor
        gameModel.setPublisher("Nintendo"); // Atribui o nome do publisher
    }


    @Test
    void getAll_ReturnsOkResponse() {
        List<GameModel> games = Arrays.asList(gameModel);

        // Configurando o mock do serviço
        when(MockZeldaGatewayService.getAll(0, 10)).thenReturn(games);

        // Chamando o método do controlador
        ResponseEntity<List<GameModel>> response = zeldaController.getAll(0, 10);

        // Verificando se a resposta é OK e se contém os dados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Zelda", response.getBody().get(0).getName());
    }

    @Test
    void getById_ReturnsOkResponse_WhenGameFound() {
        // Configurando o mock do serviço
        when(MockZeldaGatewayService.getById(anyString())).thenReturn(gameModel);

        // Chamando o método do controlador
        ResponseEntity<GameModel> response = zeldaController.getById(gameModel.getId());

        // Verificando se a resposta é OK e contém o jogo correto
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gameModel.getName(), response.getBody().getName());
    }

    @Test
    void getById_ReturnsNotFound_WhenGameNotFound() {
        // Configurando o mock do serviço
        when(MockZeldaGatewayService.getById(anyString())).thenReturn(null);

        // Chamando o método do controlador
        ResponseEntity<GameModel> response = zeldaController.getById(gameModel.getId());

        // Verificando se a resposta é Not Found
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getById_ReturnsBadRequest_WhenInvalidId() {
        // Configurando o mock do serviço para lançar uma IllegalArgumentException
        when(MockZeldaGatewayService.getById(anyString())).thenThrow(new IllegalArgumentException());

        // Chamando o método do controlador
        ResponseEntity<GameModel> response = zeldaController.getById(gameModel.getId());

        // Verificando se a resposta é Bad Request
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllByName_ReturnsOkResponse() {
        List<GameModel> games = Arrays.asList(gameModel);

        // Configurando o mock do serviço
        when(MockZeldaGatewayService.getAllByName(anyString())).thenReturn(games);

        // Chamando o método do controlador
        ResponseEntity<List<GameModel>> response = zeldaController.getAllByName("Zelda");

        // Verificando se a resposta é OK e contém os dados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Zelda", response.getBody().get(0).getName());
    }
}
