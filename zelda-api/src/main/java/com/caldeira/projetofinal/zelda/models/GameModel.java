package com.caldeira.projetofinal.zelda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("developer")
    private String developer;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("id")
    private String id;

}
