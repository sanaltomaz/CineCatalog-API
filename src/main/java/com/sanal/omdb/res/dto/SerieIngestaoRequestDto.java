package com.sanal.omdb.res.dto;

public record SerieIngestaoRequestDto(
    String nome
) {
    public static record IngestaoResponseDto(
            String titulo,
            int totalTemporadas,
            int totalEpisodios,
            boolean criada
    ) {
    }
}
