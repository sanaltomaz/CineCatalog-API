package com.sanal.omdb.models;

/**
 * Entidade de domínio que representa um episódio de uma temporada.
 *
 * <p>
 * {@code Episodio} é uma entidade fraca e não possui autonomia de criação.
 * Sua instância só pode existir dentro do contexto de uma {@link Temporada}.
 *
 * <p>
 * Esta classe mantém apenas invariantes locais e não governa
 * regras estruturais do domínio.
 */
public final class Episodio {

    private final String titulo;
    private final int numeroEpisodio;
    private final String avaliacao;

    Episodio(
            String titulo,
            int numeroEpisodio,
            String avaliacao
    ) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título do episódio é obrigatório.");
        }
        if (numeroEpisodio <= 0) {
            throw new IllegalArgumentException("O número do episódio deve ser maior que zero.");
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

    public String getAvaliacao() {
        return avaliacao;
    }
}
