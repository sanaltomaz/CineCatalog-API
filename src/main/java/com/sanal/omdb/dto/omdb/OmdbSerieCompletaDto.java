package com.sanal.omdb.dto.omdb;

import java.util.List;

/**
 * DTO agregado que representa uma série completa da OMDB,
 * contendo os metadados da série e todas as suas temporadas.
 *
 * Observações:
 * - NÃO representa um objeto de domínio
 * - NÃO mapeia diretamente uma resposta da OMDB
 * - Serve como estrutura intermediária para análise e orquestração
 *
 * Motivo de existir:
 * - Evitar poluir o OmdbSerieDto original
 * - Tornar explícita a agregação de temporadas
 * - Facilitar análise e evolução futura (cache, persistência, etc.)
 */
public record OmdbSerieCompletaDto(
    OmdbSerieDto serie,
    List<OmdbTemporadaDto> temporadas
) {
}
