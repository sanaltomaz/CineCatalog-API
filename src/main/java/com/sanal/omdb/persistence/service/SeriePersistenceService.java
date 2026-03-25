package com.sanal.omdb.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanal.omdb.models.Serie;
import com.sanal.omdb.models.Temporada;
import com.sanal.omdb.persistence.entity.SerieEntity;
import com.sanal.omdb.persistence.mapper.SerieEntityMapper;
import com.sanal.omdb.persistence.repository.EpisodioRepository;
import com.sanal.omdb.persistence.repository.SerieRepository;

/**
 * Service responsável pela persistência de séries.
 *
 * <p>
 * Atua como fronteira entre o domínio e a camada JPA.
 * Recebe objetos de domínio já válidos e os persiste
 * como entidades JPA.
 *
 * <p>
 * Este service NÃO:
 * - Busca dados externos
 * - Cria objetos de domínio
 * - Decide fluxo de aplicação
 * - Persiste episódios ou temporadas
 */
@Service
public class SeriePersistenceService {

    private final SerieRepository serieRepository;
    private final SerieEntityMapper mapper;
    private final EpisodioPersistenceService episodioService;
    private final EpisodioRepository episodioRepository;

    public SeriePersistenceService(
            SerieRepository serieRepository,
            SerieEntityMapper mapper,
            EpisodioPersistenceService episodioService,
            EpisodioRepository episodioRepository
    ) {
        this.serieRepository = serieRepository;
        this.mapper = mapper;
        this.episodioService = episodioService;
        this.episodioRepository = episodioRepository;
    }

    @Transactional
    public void saveSnapshot(Serie serie) {

        SerieEntity entity = serieRepository
            .findByTitulo(serie.getTitulo())
            .orElse(null);
        
        if (entity == null) {
            entity = mapper.toEntity(serie);
            serieRepository.save(entity);
        } else {
            episodioRepository.deleteBySerieId(entity.getId());

            entity.setAvaliacao(serie.getAvaliacao());
            entity.setDataLancamento(serie.getDataLancamento());
            entity.setSinopse(serie.getSinopse());
            entity.setTotalTemporadas(serie.getTemporadas().size());
        }

        for (Temporada t : serie.getTemporadas()) {
            episodioService.salvarTemporada(
                    entity, t.getNumero(), t.getEpisodios());
        }
    }

    public boolean findByTitulo(String titulo) {
        return serieRepository.existsByTitulo(titulo);
    }
}