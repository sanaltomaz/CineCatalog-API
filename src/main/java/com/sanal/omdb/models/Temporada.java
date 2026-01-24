package com.sanal.omdb.models;

import java.util.HashSet;
import java.util.Set;

public final class Temporada {

    private final int numero;
    private final Set<Integer> episodios = new HashSet<>();

    private Temporada(int numero) {
        this.numero = numero;
    }

    public static Temporada criar(int numero) {
        if (numero < 1) {
            throw new IllegalArgumentException("Número da temporada deve ser maior que zero.");
        }
        return new Temporada(numero);
    }

    public void adicionarEpisodio(int numeroEpisodio) {
        if (numeroEpisodio < 1) {
            throw new IllegalArgumentException("O número do episódio deve ser maior que zero.");
        }

        if (!episodios.add(numeroEpisodio)) {
            throw new IllegalArgumentException("O episódio já existe nesta temporada.");
        }
    }

    public int getNumero() {
        return numero;
    }

    public Set<Integer> getEpisodios() {
        return Set.copyOf(episodios);
    }
}
