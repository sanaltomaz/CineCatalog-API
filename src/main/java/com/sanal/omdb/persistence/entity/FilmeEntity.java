package com.sanal.omdb.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Entidade que representa um filme persistido no sistema.
 *
 * Especialização de TituloEntity.
 * Contém apenas os atributos específicos de filmes.
 *
 * Não contém lógica de negócio.
 */
@Entity
public class FilmeEntity extends TituloEntity {

    @Column(name = "duracao_minutos")
    private Double duracao;

    protected FilmeEntity() {
        // construtor exigido pelo JPA
    }

    public Double getDuracao() {
        return duracao;
    }
}
