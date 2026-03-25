package com.sanal.omdb.application.injestao.result;

public record SerieIngestaoResultado(
        String titulo,
        int totalTemporadas,
        int totalEpisodios,
        boolean criada
) {
}
