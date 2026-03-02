package com.sanal.omdb.models;

import java.time.LocalDate;

/**
 * Entidade de domínio que representa um filme.
 *
 * <p>
 * {@code Filme} é uma entidade independente no domínio e não pertence
 * a nenhum aggregate. Seu ciclo de vida não depende de outras entidades.
 *
 * <p>
 * Mantém apenas invariantes locais e não governa nem delega
 * comportamentos para outras partes do domínio.
 *
 * <p>
 * Esta classe não contém lógica de persistência, integração externa
 * ou regras de agregação.
 */
public final class Filme {

    private final String titulo;
    private final Integer duracao;
    private final String avaliacao;
    private final String sinopse;
    private final LocalDate dataLancamento;

    private Filme(
            String titulo,
            Integer duracao,
            String avaliacao,
            String sinopse,
            LocalDate dataLancamento
    ) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.avaliacao = avaliacao;
        this.sinopse = sinopse;
        this.dataLancamento = dataLancamento;
    }

    /**
     * Cria um filme válido garantindo suas invariantes locais.
     */
    public static Filme criar(
            String titulo,
            Integer duracao,
            String avaliacao,
            String sinopse,
            LocalDate dataLancamento
    ) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título do filme é obrigatório.");
        }

        if (duracao == null || duracao <= 0) {
            throw new IllegalArgumentException("A duração do filme é obrigatória.");
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

    public String getAvaliacao() {
        return avaliacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
}
