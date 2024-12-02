package com.caldeira.projetofinal.zelda.services;

import com.caldeira.projetofinal.zelda.models.GameModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
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
            System.err.println("Erro aobuscar jogo por ID: " + e.getMessage());
            return null;
        }
    }
}
