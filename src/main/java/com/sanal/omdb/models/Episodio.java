package com.sanal.omdb.models;

public final class Episodio {
    
    private final String titulo;
    private final int numeroEpisodio;
    private final Double avaliacao;

    Episodio(
            String titulo, 
            int numeroEpisodio, 
            Double avaliacao
    ) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título do episódio é obrigatório.");
        }
        if (numeroEpisodio <= 0) {
            throw new IllegalArgumentException("O número do episódio deve ser maior que zero.");
        }
        if (avaliacao != null && (avaliacao < 0 || avaliacao > 10)) {
            throw new IllegalArgumentException("A avaliação do episódio deve estar entre 0 e 10.");
        }
        this.titulo = titulo;
        this.numeroEpisodio = numeroEpisodio;
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    
}
