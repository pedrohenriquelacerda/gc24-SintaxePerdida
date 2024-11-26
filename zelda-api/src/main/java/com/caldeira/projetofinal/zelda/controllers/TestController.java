package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameListResponseModel;
import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.models.GameResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

        public TestController() {
        }

        @GetMapping("/test")
        public GameListResponseModel test() {
                String url = "https://zelda.fanapis.com/api/games";
                RestTemplate restTemplate = new RestTemplate();

                return restTemplate.getForObject(url, GameListResponseModel.class);
        }

        @GetMapping("/test2")
        public List<GameModel> test2() {
                String url = "https://zelda.fanapis.com/api/games";
                RestTemplate restTemplate = new RestTemplate();

                GameListResponseModel response = restTemplate.getForObject(url, GameListResponseModel.class);

                if (response != null) {
                        return response.getData();
                }

                return Collections.emptyList();
        }

        @GetMapping("/game/{id}")
        public GameResponseModel getGameById(@PathVariable String id) {
                String url = "https://zelda.fanapis.com/api/games/" + id;
                RestTemplate restTemplate = new RestTemplate();

                return restTemplate.getForObject(url, GameResponseModel.class);
        }
}
