package com.sanal.omdb.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Entidade que representa uma série persistida no sistema.
 *
 * Especialização de TituloEntity.
 * Armazena apenas metadados básicos da série.
 */
@Entity
public class SerieEntity extends TituloEntity {

    @Column(name = "total_temporadas")
    private Integer totalTemporadas;

    protected SerieEntity() {
        // construtor exigido pelo JPA
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }
}
