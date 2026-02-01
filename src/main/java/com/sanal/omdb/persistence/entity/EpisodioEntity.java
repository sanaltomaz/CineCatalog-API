package com.sanal.omdb.persistence.entity;

import jakarta.persistence.*;

/**
 * Entidade que representa um episódio de uma série.
 *
 * <p>
 * Episódios pertencem a uma única série e não existem de forma independente
 * no domínio principal do sistema.
 *
 * <p>
 * Decisões de modelagem:
 * - Episódio NÃO herda de TituloEntity
 * - Episódios são sempre vinculados a uma Série
 * - Temporada é representada como número inteiro
 *
 * <p>
 * Observações:
 * - Esta entidade não contém lógica de negócio
 * - Persistência ocorre exclusivamente via services
 */
@Entity
@Table(name = "episodio_entity")
public class EpisodioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "numero_episodio", nullable = false)
    private int numeroEpisodio;

    @Column(name = "numero_temporada", nullable = false)    
    private int numeroTemporada;

    private Double avaliacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serie_id", nullable = false)
    private SerieEntity serie;

    public EpisodioEntity() {
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public SerieEntity getSerie() {
        return serie;
    }

    // Setters
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setSerie(SerieEntity serie) {
        this.serie = serie;
    }
}
