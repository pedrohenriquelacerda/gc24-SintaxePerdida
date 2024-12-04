package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.models.GameResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
    private final String apiUrl = "https://zelda.fanapis.com/api/games/";

    public ZeldaGatewayService(RestTemplate restTemplate) {
                this.restTemplate = restTemplate;
    }

    public List<GameModel> getAll(Integer page, Integer size) {
        // define padrao para page e size caso sejam nulos
        int defaltPage = (page == null) ? 0 : page;
        int defaltSize = (size == null) ? 5 : size;

        String url = "https://zelda.fanapis.com/api/games?limit=" + defaltSize + "&page=" + defaltPage;

        GameListResponseModel gameListResponse =  restTemplate.getForObject(url, GameListResponseModel.class);

        // chama a API
        assert gameListResponse != null;
        GameModel[] response = gameListResponse.getData().toArray(new GameModel[0]);
        return List.of(response);
    }


    public GameModel getById(String id) {
        String url = "https://zelda.fanapis.com/api/games/" + id;

        try {
            GameResponseModel gameResponse = restTemplate.getForObject(url, GameResponseModel.class);
            assert gameResponse != null;
            return gameResponse.getData();

        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao buscar jogo por ID: " + e.getMessage());
            return null;
        }
    }
    
    public List<GameModel> getAllByName(String name) {
        String url = "https://zelda.fanapis.com/api/games?name=" + name.substring(0, 1).toUpperCase() + name.substring(1);

        GameListResponseModel gameListResponse =  restTemplate.getForObject(url, GameListResponseModel.class);

        // chama a API

        assert gameListResponse != null;
        GameModel[] response = gameListResponse.getData().toArray(new GameModel[0]);

        return List.of(response);
    }
}
