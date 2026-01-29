package com.sanal.omdb.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.sanal.omdb.dto.omdb.OmdbFilmeDto;
import com.sanal.omdb.dto.omdb.OmdbSerieDto;
import com.sanal.omdb.models.Filme;
import com.sanal.omdb.models.Serie;

/**
 * Factory responsável por converter DTOs da OMDB
 * em objetos de domínio.
 *
 * <p>
 * Atua exclusivamente como fronteira entre:
 * - Integração externa (OMDB)
 * - Núcleo do domínio
 *
 * <p>
 * Não decide fluxo, não conhece persistência
 * e não cria entidades dependentes de contexto estrutural.
 */
public class OmdbDomainFactory {

    private static final DateTimeFormatter OMDB_DATE_FORMAT =
        DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    public Filme criarFilme(OmdbFilmeDto dto) {
        return Filme.criar(
            dto.titulo(),
            parseDuracao(dto.duracao()),
            parseAvaliacao(dto.avaliacao()),
            null,
            parseData(dto.dataLancamento())
        );
    }

    public Serie criarSerie(OmdbSerieDto dto) {
        return Serie.criar(
            dto.titulo(),
            parseAvaliacao(dto.avaliacao()),
            parseData(dto.dataLancamento()),
            null
        );
    }

    /* =======================
       Parsing utilitário
       ======================= */

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

    private Integer parseDuracao(String duracao) {
        if (duracao == null || duracao.equalsIgnoreCase("N/A")) {
            return null;
        }
        try {
            return Integer.valueOf(duracao.replace(" min", "").trim());
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate parseData(String dataLancamento) {
        if (dataLancamento == null ||
            dataLancamento.isBlank() ||
            dataLancamento.equalsIgnoreCase("N/A")) {
            return null;
        }

        try {
            return LocalDate.parse(dataLancamento, OMDB_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
