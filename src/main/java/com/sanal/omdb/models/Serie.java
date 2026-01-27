package com.sanal.omdb.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public final class Serie {
    private final String titulo;
    private final Double avaliacao;
    private final LocalDate dataLancamento;
    private final String sinopse;
    private final Set<Temporada> temporadas = new HashSet<>();

    private Serie(
            String titulo,
            Double avaliacao,
            LocalDate dataLancamento,
            String sinopse
    ) {
        this.titulo = titulo;
        this.avaliacao = avaliacao;
        this.dataLancamento = dataLancamento;
        this.sinopse = sinopse;
    }

    public static Serie criar (
            String titulo,
            Double avaliacao,
            LocalDate dataLancamento,
            String sinopse
    ) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título da série é obrigatório.");
        }

        if (avaliacao != null && (avaliacao < 0 || avaliacao > 10)) {
            throw new IllegalArgumentException("A avaliação da série deve estar entre 0 e 10.");
        }

        return new Serie(
            titulo, 
            avaliacao, 
            dataLancamento, 
            sinopse
        );
    }

    public Episodio criarEpisodio(
            int numeroTemporada,
            String titulo,
            int numeroEpisodio,
            Double avaliacao
    ) {
        Temporada temporada = obterOuCriarTemporada(numeroTemporada);
        return temporada.criarEpisodio(titulo, numeroEpisodio, avaliacao);
    }

    private Temporada obterOuCriarTemporada(int numeroTemporada) {
        return temporadas.stream()
            .filter(t -> t.getNumero() == numeroTemporada)
            .findFirst()
            .orElseGet(() -> {
                Temporada nova = Temporada.criar(numeroTemporada);
                temporadas.add(nova);
                return nova;
            });
    }

    public String getTitulo() {
        return titulo;
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

    public Set<Temporada> getTemporadas() {
        return Set.copyOf(temporadas);
    }

}
