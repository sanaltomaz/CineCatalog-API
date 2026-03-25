package com.sanal.omdb.application.injestao.dto;

public record SerieIngestaoResponseDto(
        Long id,
        String titulo,
        String avaliacao,
        String sinopse,
        Integer totalTemporadas
) {
}
