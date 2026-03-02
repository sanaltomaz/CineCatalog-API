package com.sanal.omdb.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Entidade de domínio responsável por governar os episódios de uma temporada.
 *
 * <p>
 * A {@code Temporada} possui autoridade exclusiva sobre a criação e organização
 * de {@link Episodio}. Episódios não existem fora do contexto de uma temporada.
 *
 * <p>
 * A {@code Temporada} não conhece outras temporadas nem decide fluxos globais
 * da série. Essas decisões pertencem à {@link Serie}.
 *
 * <p>
 * Esta classe não contém lógica de persistência nem integração externa.
 */
public final class Temporada {

    private final int numero;

    /**
     * Define a fronteira de autoridade da {@code Temporada}:
     * episódios não existem fora deste contexto e só podem ser criados
     * por meio desta entidade.
     */
    private final Set<Episodio> episodios = new HashSet<>();

    private Temporada(int numero) {
        this.numero = numero;
    }

    /**
     * Cria uma temporada válida garantindo suas invariantes básicas.
     */
    public static Temporada criar(int numero) {
        if (numero < 1) {
            throw new IllegalArgumentException("Número da temporada deve ser maior que zero.");
        }
        return new Temporada(numero);
    }

    /**
     * Cria um episódio pertencente a esta temporada.
     *
     * <p>
     * Garante que não existam episódios duplicados pelo número
     * dentro da mesma temporada.
     *
     * <p>
     * A {@code Temporada} não valida regras internas do episódio,
     * delegando essa responsabilidade à própria entidade {@link Episodio}.
     */
    public Episodio criarEpisodio(
            String titulo, 
            int numero, 
            String avaliacao
    ) {
        boolean existe = episodios.stream()
            .anyMatch(t -> t.getNumeroEpisodio() == numero);

        if (existe) {
            throw new IllegalArgumentException(
                "Já existe episódio com o número %d nesta temporada."
                    .formatted(numero)
            );
        }

        Episodio episodio = new Episodio(titulo, numero, avaliacao);
        episodios.add(episodio);
        return episodio;
    }

    public int getNumero() {
        return numero;
    }

    /**
     * Retorna os episódios da temporada de forma imutável.
     *
     * <p>
     * Qualquer modificação deve ocorrer exclusivamente
     * por métodos do domínio.
     */
    public Set<Episodio> getEpisodios() {
        return Set.copyOf(episodios);
    }
}
