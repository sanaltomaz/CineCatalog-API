package com.sanal.omdb.persistence.mapper;

import com.sanal.omdb.models.Episodio;
import com.sanal.omdb.persistence.entity.EpisodioEntity;
import com.sanal.omdb.persistence.entity.SerieEntity;

/**
 * Mapper responsável por converter dados de episódios
 * para entidades de persistência.
 *
 * Responsabilidades:
 * - Converter dados vindos da OMDB em EpisodioEntity
 * - Garantir vínculo com uma SerieEntity já persistida
 *
 * NÃO faz:
 * - Persistência em banco
 * - Busca de série
 * - Decisão de fluxo
 * - Validação de regras de negócio (apenas invariantes estruturais)
 *
 * Observações:
 * - Episódios sempre dependem de uma série existente
 * - Este mapper não cria entidades órfãs
 */
public class EpisodioEntityMapper {

    public EpisodioEntity toEntity(
        Episodio episodio,
        SerieEntity serie,
        int numeroTemporada
    ) {
        EpisodioEntity entity = new EpisodioEntity();
        entity.setTitulo(episodio.getTitulo());
        entity.setNumeroEpisodio(episodio.getNumeroEpisodio());
        entity.setNumeroTemporada(numeroTemporada);
        entity.setAvaliacao(episodio.getAvaliacao());
        entity.setSerie(serie);

        return entity;
    }
}

