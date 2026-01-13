package com.sanal.omdb.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanal.omdb.dto.omdb.OmdbSerieCompletaDto;
import com.sanal.omdb.dto.omdb.OmdbTemporadaDto;
import com.sanal.omdb.persistence.entity.SerieEntity;

/**
 * Service responsável exclusivamente pela persistência de episódios.
 *
 * Responsabilidades:
 * - Persistir episódios associados a uma série existente
 * - Garantir vínculo correto entre episódio e série
 *
 * NÃO faz:
 * - Busca na OMDB
 * - Criação de séries
 * - Análises ou estatísticas
 * - Decisão se episódio deve ou não existir
 *
 * Observações:
 * - Série deve existir antes da persistência
 * - Métodos podem ser custosos (persistência em lote)
 */
@Service
public class EpisodioPersistenciaService {

    /**
     * Persiste todos os episódios de uma temporada específica.
     *
     * Pré-condições:
     * - Série já persistida
     * - Temporada já carregada da OMDB
     *
     * @param serie série persistida
     * @param temporadaDto dados da temporada
     */
    @Transactional
    public void salvarEpisodiosTemporada(
        SerieEntity serie,
        OmdbTemporadaDto temporadaDto
    ) {
        // Implementação será feita em etapa posterior
    }

    /**
     * Persiste todos os episódios de uma série completa.
     *
     * Observação:
     * - Método potencialmente custoso
     *
     * @param serie série persistida
     * @param serieCompletaDto dados completos da série
     */
    @Transactional
    public void salvarTodosEpisodios(
        SerieEntity serie,
        OmdbSerieCompletaDto serieCompletaDto
    ) {
        // Implementação será feita em etapa posterior
    }
}
