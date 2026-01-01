package com.sanal.omdb.models;

import java.time.LocalDate;

public class Titulo {

    private final TipoTitulo tipo;
    private final String titulo;
    private final Integer temporadas;
    private final Integer numeroEpisodio;
    private final Double duracao;
    private final Double avaliacao;
    private final LocalDate dataLancamento;
    private final String sinopse;

    public Titulo(
            TipoTitulo tipo,
            String titulo,
            Integer temporadas,
            Integer numeroEpisodio,
            Double duracao,
            Double avaliacao,
            LocalDate dataLancamento,
            String sinopse
    ) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.temporadas = temporadas;
        this.numeroEpisodio = numeroEpisodio;
        this.duracao = duracao;
        this.avaliacao = avaliacao;
        this.dataLancamento = dataLancamento;
        this.sinopse = sinopse;
    }

    public TipoTitulo getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getTemporadas() {
        return temporadas;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public Double getDuracao() {
        return duracao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public String getSinopse() {
        return sinopse;
    }

    @Override
    public String toString() {
        return switch (tipo) {
            case FILME -> """
                Filme: %s
                Avaliação: %.1f
                Duração: %.0f min
                Lançamento: %s
                Sinopse: %s
                """.formatted(titulo, avaliacao, duracao, dataLancamento, sinopse);

            case SERIE -> """
                Série: %s
                Temporadas: %d
                Avaliação: %.1f
                Lançamento: %s
                Sinopse: %s
                """.formatted(titulo, temporadas, avaliacao, dataLancamento, sinopse);

            case EPISODIO -> """
                Episódio: %s
                Número: %d
                Avaliação: %.1f
                """.formatted(titulo, numeroEpisodio, avaliacao);
        };
    }
}
