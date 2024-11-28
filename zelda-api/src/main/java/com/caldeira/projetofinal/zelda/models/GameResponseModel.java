package com.caldeira.projetofinal.zelda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameResponseModel {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private GameModel data;
}
