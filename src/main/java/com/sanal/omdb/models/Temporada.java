package com.sanal.omdb.models;

import java.util.HashSet;
import java.util.Set;

public final class Temporada {

    private final int numero;
    private final Set<Episodio> episodios = new HashSet<>();

    private Temporada(int numero) {
        this.numero = numero;
    }

    public static Temporada criar(int numero) {
        if (numero < 1) {
            throw new IllegalArgumentException("Número da temporada deve ser maior que zero.");
        }
        return new Temporada(numero);
    }

    public Episodio criarEpisodio(String titulo, int numero, Double avaliacao) {
        boolean existe = episodios.stream()
            .anyMatch(t -> t.getNumeroEpisodio() == numero);

        if (existe) {
            throw new IllegalArgumentException("Já existe episódio com o número %d nesta temporada."
                .formatted(numero)
            );
        }

        Episodio e = new Episodio(titulo, numero, avaliacao);

        episodios.add(e);
        return e;
    }


    public int getNumero() {
        return numero;
    }

    public Set<Episodio> getEpisodios() {
        return Set.copyOf(episodios);
    }
}
