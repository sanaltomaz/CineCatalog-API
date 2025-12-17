package com.sanal.omdb.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(
    @JsonAlias("Season") Integer numeroTemporada,
    @JsonAlias("Episodes") DadosEpisodio[] episodios
) {
}
