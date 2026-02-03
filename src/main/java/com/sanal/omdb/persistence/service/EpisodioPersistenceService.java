package com.sanal.omdb.persistence.service;

import org.springframework.stereotype.Service;

import com.sanal.omdb.models.Episodio;
import com.sanal.omdb.persistence.entity.EpisodioEntity;
import com.sanal.omdb.persistence.entity.SerieEntity;
import com.sanal.omdb.persistence.mapper.EpisodioEntityMapper;
import com.sanal.omdb.persistence.repository.EpisodioRepository;

@Service
public class EpisodioPersistenceService {

    private final EpisodioRepository episodioRepository;
    private final EpisodioEntityMapper mapper;

    public EpisodioPersistenceService(
            EpisodioRepository episodioRepository,
            EpisodioEntityMapper mapper
    ) {
        this.episodioRepository = episodioRepository;
        this.mapper = mapper;
    }

    /**
     * Persiste um episódio já validado pelo domínio.
     *
     * <p>
     * Pré-condições assumidas:
     * - O episódio já respeita todas as invariantes de domínio
     * - O episódio foi criado por uma {@link Temporada}
     *
     * <p>
     * Validações realizadas aqui são apenas estruturais,
     * relacionadas ao uso correto da camada de persistência.
     */
    public void salvarEpisodio(
            Episodio episodio,
            SerieEntity serie,
            int numeroTemporada
    ) {
        if (serie == null || serie.getId() == null) {
            throw new IllegalStateException(
                "Série deve estar previamente persistida antes de salvar episódios"
            );
        }

        if (numeroTemporada < 1) {
            throw new IllegalArgumentException(
                "Número de temporada inválido para persistência"
            );
        }

        EpisodioEntity entity = mapper.toEntity(episodio, serie, numeroTemporada);
        episodioRepository.save(entity);
    }
}
