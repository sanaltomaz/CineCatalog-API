package com.sanal.omdb.persistence.mapper;

import com.sanal.omdb.dto.omdb.OmdbEpisodioDto;
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
 * - Validação de regras de negócio
 *
 * Observações:
 * - Episódios sempre dependem de uma série existente
 * - Este mapper não cria entidades órfãs
 */
public class EpisodioEntityMapper {

    /**
     * Converte dados de um episódio em uma entidade persistível.
     *
     * Pré-condições:
     * - Série já persistida
     * - Dados do episódio já carregados da OMDB
     *
     * @param episodioDto dados do episódio
     * @param serie série persistida
     * @param numeroTemporada número da temporada
     * @return EpisodioEntity pronta para persistência
     */
    public EpisodioEntity toEntity(
        OmdbEpisodioDto episodioDto,
        SerieEntity serie,
        Integer numeroTemporada
    ) {
        // Implementação será feita em etapa posterior
        return new EpisodioEntity();
    }
}
