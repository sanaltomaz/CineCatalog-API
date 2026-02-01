package com.sanal.omdb.persistence.mapper;

import com.sanal.omdb.models.Filme;
import com.sanal.omdb.persistence.entity.FilmeEntity;

/**
 * Mapper responsável por converter entre {@link Filme}
 * (domínio) e {@link FilmeEntity} (persistência).
 *
 * <p>
 * Trabalha exclusivamente com os metadados do filme.
 * Não envolve regras de negócio nem decisões de fluxo.
 */
public class FilmeEntityMapper {
    
    /**
     * Converte uma {@link FilmeEntity} persistida em {@link Filme} de domínio.
     */
    public Filme toDomain(FilmeEntity entity) {
        return Filme.criar(
            entity.getTitulo(),
            entity.getDuracao(),
            entity.getAvaliacao(),
            entity.getSinopse(),
            entity.getDataLancamento()
        );
    }

    /**
     * Converte uma {@link Filme} de domínio em {@link FilmeEntity}.
     */
    public FilmeEntity toEntity(Filme domain) {
        FilmeEntity entity = new FilmeEntity();
        entity.setTitulo(domain.getTitulo());
        entity.setDuracao(domain.getDuracao());
        entity.setAvaliacao(domain.getAvaliacao());
        entity.setSinopse(domain.getSinopse());
        entity.setDataLancamento(domain.getDataLancamento());

        return entity;
    }
}
