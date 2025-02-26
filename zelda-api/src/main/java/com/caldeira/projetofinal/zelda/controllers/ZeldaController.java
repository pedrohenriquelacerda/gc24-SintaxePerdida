package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import com.caldeira.projetofinal.zelda.services.ZeldaGatewayService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RestController
public class ZeldaController {
    private final ZeldaGatewayService zeldaService;

    public ZeldaController(ZeldaGatewayService zeldaService) {
        this.zeldaService = zeldaService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/all")
    public ResponseEntity<List<GameModel>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        List<GameModel> games = zeldaService.getAll(page, size);
        return ResponseEntity.ok(games);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<GameModel> getById(@PathVariable String id) {
        try {
            GameModel game = zeldaService.getById(String.valueOf(id));
            if (game != null) {
                return ResponseEntity.ok(game);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<GameModel>> getAllByName(@PathVariable String name) {
        List<GameModel> games = zeldaService.getAllByName(name);
        if (!games.isEmpty()) {
            return ResponseEntity.ok(games);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
