package com.sanal.omdb.persistence.mapper;

import org.springframework.stereotype.Component;

import com.sanal.omdb.models.Serie;
import com.sanal.omdb.persistence.entity.SerieEntity;

/**
 * Mapper responsável por converter entre {@link Serie}
 * (domínio) e {@link SerieEntity} (persistência).
 *
 * <p>
 * Este mapper trabalha apenas com os metadados básicos da série.
 * Temporadas e episódios NÃO são carregados nem persistidos aqui.
 */
@Component
public class SerieEntityMapper {

    /**
     * Converte uma {@link SerieEntity} de persistência em {@link Serie} de domínio.
     * 
     * <p>
     * A série retornada NÃO possui temporadas carregadas.
     * O carregamento de temporadas é responsabilidade de services de aplicação
     * ou persistência.
     */
    public Serie toDomain(SerieEntity entity) {
        return Serie.criar(
            entity.getTitulo(),
            entity.getAvaliacao(),
            entity.getDataLancamento(),
            entity.getSinopse()
        );
    }

    /**
     * Converte uma {@link Serie} de domínio em {@link SerieEntity}.
     *
     * <p>
     * {@code totalTemporadas} é persistido como snapshot e não governa
     * o carregamento de temporadas ou episódios.
     */
    public SerieEntity toEntity(Serie domain) {
        SerieEntity entity = new SerieEntity();
        entity.setTitulo(domain.getTitulo());
        entity.setAvaliacao(domain.getAvaliacao());
        entity.setSinopse(domain.getSinopse());
        entity.setDataLancamento(domain.getDataLancamento());
        entity.setTotalTemporadas(domain.getTemporadas().size());

        return entity;
    }
}
