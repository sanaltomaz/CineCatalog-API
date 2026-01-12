package com.sanal.omdb.persistence.entity;

import jakarta.persistence.*;

/**
 * Entidade que representa um episódio de uma série.
 *
 * Episódios pertencem a uma única série e não existem de forma independente
 * no domínio principal do sistema.
 *
 * Esta entidade não herda de TituloEntity por não representar
 * um título principal.
 */
@Entity
@Table(name = "episodio")
public class EpisodioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "numero_episodio")
    private Integer numeroEpisodio;

    @Column(name = "numero_temporada")
    private Integer numeroTemporada;

    private Double avaliacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id", nullable = false)
    private SerieEntity serie;

    protected EpisodioEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public Integer getNumeroTemporada() {
        return numeroTemporada;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public SerieEntity getSerie() {
        return serie;
    }
}
