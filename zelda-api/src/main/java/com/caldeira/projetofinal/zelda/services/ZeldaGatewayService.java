package com.caldeira.projetofinal.zelda.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ZeldaGatewayService {
    private final RestTamplate restTemplate;

    public zeldaGatewayService(RestTemplate restTemplate) {
                this.restTemplate = restTemplate;
    }
}
