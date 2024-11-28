package com.caldeira.projetofinal.zelda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameListResponseModel {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("data")
    private List<GameModel> data;
}
