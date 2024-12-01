package com.caldeira.projetofinal.zelda.controllers;

import com.caldeira.projetofinal.zelda.models.GameModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
