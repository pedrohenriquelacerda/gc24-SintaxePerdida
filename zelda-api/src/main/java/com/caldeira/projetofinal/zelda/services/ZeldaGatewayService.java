package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZeldaGatewayService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://api.exemplo.com/games/";

    public ZeldaGatewayService(RestTemplate restTemplate) {
                this.restTemplate = restTemplate;
    }

    public List<GameModel> getAll(Integer page, Integer size) {
        // define padrao para page e size caso sejam nulos
        int defaltPage = (page == null) ? 0 : page;
        int defaltSize = (size == null) ? 6 : size;

        String url = "https: //apiexemplo.com/games?page=" + defaltPage + "&size=" + defaltSize;

        // chama a API
        GameModel[] response = restTemplate.getForObject(url, GameModel[].class);
        return List.of(response);
    }

    public GameModel getById(String id) {
        String url = apiUrl + id;

        try {
            return restTemplate.getForObject(url, GameModel.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao buscar jogo por ID: " + e.getMessage());
            return null;
        }
    }
    
    public List<GameModel> getAllByName(String name) {
        List<GameModel> games = new ArrayList<>();
        try {
            String url = apiUrl + name;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.body());

                for (JsonNode gameNode : rootNode) {
                    GameModel game = new GameModel();
                    game.setId(gameNode.get("id").asInt());
                    game.setName(gameNode.get("name").asText());
                    game.setDescription(gameNode.get("description").asText());
                    games.add(game);

                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar jogos: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return games;
    }
}
