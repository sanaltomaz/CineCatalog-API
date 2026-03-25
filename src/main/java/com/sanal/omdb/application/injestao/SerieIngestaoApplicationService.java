package com.sanal.omdb.application.injestao;

import com.sanal.omdb.application.injestao.result.SerieIngestaoResultado;
import org.springframework.stereotype.Service;

import com.sanal.omdb.dto.omdb.OmdbEpisodioDto;
import com.sanal.omdb.dto.omdb.OmdbSerieDto;
import com.sanal.omdb.dto.omdb.OmdbTemporadaDto;
import com.sanal.omdb.models.Serie;
import com.sanal.omdb.omdb.OmdbClient;
import com.sanal.omdb.persistence.service.SeriePersistenceService;
import com.sanal.omdb.services.OmdbDomainFactory;

@Service
public class SerieIngestaoApplicationService {

    private final OmdbClient omdbClient;
    private final OmdbDomainFactory factory;
    private final SeriePersistenceService persistenciaService;

    public SerieIngestaoApplicationService(
        OmdbClient omdbClient,
        OmdbDomainFactory factory,
        SeriePersistenceService persistenciaService
    ) {
        this.omdbClient = omdbClient;
        this.factory = factory; 
        this.persistenciaService = persistenciaService;
    }

    public SerieIngestaoResultado ingestarSerie(String nome){

        boolean criada =
                !persistenciaService.findByTitulo(nome);

        OmdbSerieDto dto = omdbClient.buscarSerie(nome);
        Serie serie = factory.criarSerie(dto);

        int total = dto.temporadas() != null ? dto.temporadas() : 0;
        int totalEpisodios = 0;

        for (int i = 1; i <= total; i++) {
            OmdbTemporadaDto tempDto =
                    omdbClient.buscarTemporada(nome, i);

            for (OmdbEpisodioDto epDto : tempDto.episodios()) {

                serie.criarEpisodio(
                    i, 
                    epDto.titulo(),
                    epDto.episodio(),
                    epDto.avaliacao()
                );
                totalEpisodios++;
            }
        }

        persistenciaService.saveSnapshot(serie);

        return new SerieIngestaoResultado(
                serie.getTitulo(),
                total,
                totalEpisodios,
                criada
        );
    }
}
