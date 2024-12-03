package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RestController
public class ZeldaController {
    private final ZeldaService zeldaService;

    @GetMapping("/all")
    public ResponseEntity<List<GameModel>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        List<GameModel> games = zeldaService.getAll(page, size);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<GameModel> getById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            GameModel game = gameService.getById(uuid);
            if (game != null) {
                return ResponseEntity.ok(game);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
