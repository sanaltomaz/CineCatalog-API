package com.sanal.omdb.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidade JPA que representa uma série persistida no sistema.
 * 
 * <p>
 * Responsabilidade:
 * - Armazenar apenas os metadados básicos da série
 *
 * <p>
 * Observações:
 * - Não contém episódios
 * - Não contém regras de negócio
 */
@Entity
public class SerieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    private Double avaliacao;
    private String sinopse;

    @Column(name = "total_temporadas", nullable = false)
    private Integer totalTemporadas;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public SerieEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }
}
