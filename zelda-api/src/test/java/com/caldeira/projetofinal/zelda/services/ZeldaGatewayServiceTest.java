package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.models.GameResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZeldaGatewayServiceTest {

    private ZeldaGatewayService zeldaGatewayService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zeldaGatewayService = new ZeldaGatewayService(restTemplate);
    }

    @Test
    void testGetAll() {
        // Arrange
        String url = "https://zelda.fanapis.com/api/games?limit=5&page=0";
        GameListResponseModel mockResponse = mock(GameListResponseModel.class);
        GameModel game1 = new GameModel();
        game1.setName("Zelda 1");
        game1.setDescription("First game in the series");
        GameModel game2 = new GameModel();
        game2.setName("Zelda 2");
        game2.setDescription("Second game in the series");

        when(restTemplate.getForObject(eq(url), eq(GameListResponseModel.class)))
                .thenReturn(mockResponse);
        when(mockResponse.getData()).thenReturn(List.of(game1, game2));

        // Act
        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Zelda 1", result.get(0).getName());
        assertEquals("Zelda 2", result.get(1).getName());
    }

    @Test
    void testGetAllResponseNull() {
        // Arrange
        String url = "https://zelda.fanapis.com/api/games?limit=5&page=0";
        // Criar uma resposta mockada com uma lista vazia
        GameListResponseModel mockResponse = mock(GameListResponseModel.class);
        when(mockResponse.getData()).thenReturn(List.of()); // Retorna uma lista vazia

        // Configurar o restTemplate para retornar a resposta mockada
        when(restTemplate.getForObject(eq(url), eq(GameListResponseModel.class)))
                .thenReturn(mockResponse);

        // Act
        List<GameModel> result = zeldaGatewayService.getAll(0, 5);

        // Assert
        assertNotNull(result);  // A lista não deve ser nula
        assertTrue(result.isEmpty());  // A lista deve estar vazia
    }


    @Test
    void testGetAllByName() {
        // Arrange
        String gameName = "Zelda";
        String url = "https://zelda.fanapis.com/api/games?name=Zelda";
        GameListResponseModel mockResponse = mock(GameListResponseModel.class);
        GameModel game1 = new GameModel();
        game1.setName("Zelda 1");
        game1.setDescription("First game in the series");
        GameModel game2 = new GameModel();
        game2.setName("Zelda 2");
        game2.setDescription("Second game in the series");

        when(restTemplate.getForObject(eq(url), eq(GameListResponseModel.class)))
                .thenReturn(mockResponse);
        when(mockResponse.getData()).thenReturn(List.of(game1, game2));

        // Act
        List<GameModel> result = zeldaGatewayService.getAllByName(gameName);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Zelda 1", result.get(0).getName());
        assertEquals("Zelda 2", result.get(1).getName());
    }

    @Test
    void testGetAllByNameResponseNull() {
        // Arrange
        String gameName = "Zelda";
        String url = "https://zelda.fanapis.com/api/games?name=Zelda";
        // Criar uma resposta mockada com uma lista vazia
        GameListResponseModel mockResponse = mock(GameListResponseModel.class);
        when(mockResponse.getData()).thenReturn(List.of()); // Retorna uma lista vazia

        // Configurar o restTemplate para retornar a resposta mockada
        when(restTemplate.getForObject(eq(url), eq(GameListResponseModel.class)))
                .thenReturn(mockResponse);

        // Act
        List<GameModel> result = zeldaGatewayService.getAllByName(gameName);

        // Assert
        assertNotNull(result);  // A lista não deve ser nula
        assertTrue(result.isEmpty());  // A lista deve estar vazia
    }


    @Test
    void testGetById() {
        // Arrange
        String gameId = "1";
        String url = "https://zelda.fanapis.com/api/games/" + gameId;
        GameResponseModel mockResponse = mock(GameResponseModel.class);
        GameModel game = new GameModel();
        game.setName("Zelda 1");
        game.setDescription("First game in the series");

        when(restTemplate.getForObject(eq(url), eq(GameResponseModel.class)))
                .thenReturn(mockResponse);
        when(mockResponse.getData()).thenReturn(game);

        // Act
        GameModel result = zeldaGatewayService.getById(gameId);

        // Assert
        assertNotNull(result);
        assertEquals("Zelda 1", result.getName());
    }

    @Test
    void testGetByIdNotFound() {
        // Arrange
        String gameId = "999";
        String url = "https://zelda.fanapis.com/api/games/" + gameId;

        // Simula que a API retorna um erro 404
        when(restTemplate.getForObject(eq(url), eq(GameResponseModel.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        // Act
        GameModel result = zeldaGatewayService.getById(gameId);

        // Assert
        assertNull(result);  // O retorno deve ser nulo quando o jogo não for encontrado
    }

    @Test
    void testGetByIdNotFoundException() {
        // Arrange
        String gameId = "999";  // ID fictício para simular que o jogo não foi encontrado
        String url = "https://zelda.fanapis.com/api/games/" + gameId;

        // Simula que a API retorna uma exceção 404 NotFound
        when(restTemplate.getForObject(eq(url), eq(GameResponseModel.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        // Act
        GameModel result = zeldaGatewayService.getById(gameId);

        // Assert
        assertNull(result);  // Como a exceção foi tratada, o retorno deve ser null
    }


    @Test
    void testGetAllByNameThrowsException() {
        // Arrange
        String gameName = "Zelda";
        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenThrow(new RuntimeException("Network error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            zeldaGatewayService.getAllByName(gameName);
        });
    }

    @Test
    void testGetAllThrowsException() {
        // Arrange
        when(restTemplate.getForObject(anyString(), eq(GameListResponseModel.class)))
                .thenThrow(new RuntimeException("Network error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            zeldaGatewayService.getAll(0, 5);
        });
    }
}
