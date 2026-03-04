package com.sanal.omdb.res.dto;

public record SerieResponseDto(
    Long id,
    String titulo,
    String avaliacao,
    String sinopse,
    Integer totalTemporadas
) { 
}
