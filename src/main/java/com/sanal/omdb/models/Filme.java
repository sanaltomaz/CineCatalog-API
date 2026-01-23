package com.sanal.omdb.models;

import java.time.LocalDate;

public class Filme {
    private final String titulo;
    private final Integer duracao;
    private final Double avaliacao;
    private final String sinopse;
    private final LocalDate dataLancamento;

    private Filme(
            String titulo,
            Integer duracao,
            Double avaliacao,
            String sinopse,
            LocalDate dataLancamento
    ) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.avaliacao = avaliacao;
        this.sinopse = sinopse;
        this.dataLancamento = dataLancamento;
    }

    public static Filme criar (
            String titulo,
            Integer duracao,
            Double avaliacao,
            String sinopse,
            LocalDate dataLancamento
    ) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título do filme é obrigatório.");
        }

        if (duracao == null || duracao <= 0) {
            throw new IllegalArgumentException("A duração do filme é obrigatória.");
        }

        if (avaliacao != null && (avaliacao < 0 || avaliacao > 10)) {
            throw new IllegalArgumentException("A avaliação do filme deve estar entre 0 e 10.");
        }

        return new Filme(
            titulo, 
            duracao, 
            avaliacao, 
            sinopse, 
            dataLancamento
        );
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

}
