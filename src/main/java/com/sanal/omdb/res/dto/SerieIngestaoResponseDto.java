package com.sanal.omdb.res.dto;

public record SerieIngestaoResponseDto(
        String titulo,
        Integer totalTemporadas,
        Integer totalEpisodios,
        boolean serieCriada
) {

    public SerieIngestaoResponseDto(Long id, String titulo2, String avaliacao, String sinopse,
            Integer totalTemporadas2) {
        this(titulo2, totalTemporadas2, null, true);
    }
}
