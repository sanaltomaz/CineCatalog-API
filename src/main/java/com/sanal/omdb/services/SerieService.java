package com.sanal.omdb.services;

import org.springframework.stereotype.Service;

import com.sanal.omdb.dto.omdb.OmdbSerieDto;
import com.sanal.omdb.dto.omdb.OmdbTemporadaDto;
import com.sanal.omdb.models.Serie;
import com.sanal.omdb.omdb.OmdbClient;

@Service
public class SerieService {

    private final OmdbClient omdbClient;
    private final OmdbDomainFactory factory;

    public SerieService(
            OmdbClient omdbClient,
            OmdbDomainFactory factory
    ) {
        this.omdbClient = omdbClient;
        this.factory = factory;
    }

    public Serie buscarSerie(String nome) {
        OmdbSerieDto dto = omdbClient.buscarSerie(nome);
        return factory.criarSerie(dto);
    }

    public Serie buscarSerieComEpisodios(String nome) {
        OmdbSerieDto dto = omdbClient.buscarSerie(nome);
        Serie serie = factory.criarSerie(dto);

        int totalTemporadas = dto.temporadas();
        if (totalTemporadas < 1) {
            return serie;
        }

        for (int numeroTemporada = 1; numeroTemporada <= totalTemporadas; numeroTemporada++) {
            OmdbTemporadaDto temporadaDto = omdbClient.buscarTemporada(nome, numeroTemporada);
                
            for (var episodioDto : temporadaDto.episodios()) {
                serie.criarEpisodio(
                    numeroTemporada,
                    episodioDto.titulo(),
                    episodioDto.episodio(),
                    parseAvaliacao(episodioDto.avaliacao())
                );
            }
        }

        return serie;
    }

    private Double parseAvaliacao(String avaliacao) {
        if (avaliacao == null || avaliacao.equalsIgnoreCase("N/A")) {
            return null;
        }
        try {
            return Double.valueOf(avaliacao);
        } catch (Exception e) {
            return null;
        }
    }
}
