package com.sanal.omdb.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilme(
    @JsonAlias("Type") String type,
    @JsonAlias("Title") String titulo,
    @JsonAlias("imdbRating") String avaliacao,
    @JsonAlias("Runtime") String duracao,
    @JsonAlias("Released") String dataLancamento 
) {
    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
               "Duração: " + duracao + "\n" +
               "Avaliação IMDb: " + avaliacao + "\n" +
               "Data de Lançamento: " + dataLancamento + "\n";
    }
}
