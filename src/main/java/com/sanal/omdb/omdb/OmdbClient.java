package com.sanal.omdb.omdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanal.omdb.dto.omdb.OmdbFilmeDto;
import com.sanal.omdb.dto.omdb.OmdbSerieDto;
import com.sanal.omdb.dto.omdb.OmdbTemporadaDto;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Concentra tudo que é específico da API do OMDB.
 *
 * <p>
 * Responsabilidades:
 * - Montagem de URLs da OMDB
 * - Chamada HTTP
 * - Tratamento básico de erros externos
 * - Conversão de JSON para DTOs externos
 *
 * <p>
 * Não contém:
 * - Regras de negócio
 * - Lógica de análise
 * - Decisão de fluxo da aplicação
 * 
 * <p>
 *  * Limitações conhecidas:
 * - Configuração via dotenv (a ser migrada para injeção de configuração)
 * - HttpClient instanciado internamente
 */ 
@Component
public class OmdbClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String endereco;
    private final String apiKey;

    public OmdbClient() {
        Dotenv dotenv = Dotenv.load();
        this.endereco = dotenv.get("ENDERECO");
        this.apiKey = dotenv.get("API_KEY");
    }


    private String consumir(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consumir API da OMDB", e);
        }
    }

    public OmdbFilmeDto buscarFilme(String nome) {
        String json = consumir(endereco + nome.replace(" ", "+") + apiKey);
        try {
            return mapper.readValue(json, OmdbFilmeDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter filme da OMDB", e);
        }
    }

    public OmdbSerieDto buscarSerie(String nome) {
        String json = consumir(endereco + nome.replace(" ", "+") + apiKey);
        try {
            return mapper.readValue(json, OmdbSerieDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter série da OMDB", e);
        }
    }

    public OmdbTemporadaDto buscarTemporada(String nomeSerie, int temporada) {
        String url = endereco
            + nomeSerie.replace(" ", "+")
            + "&Season=" + temporada
            + apiKey;

        String json = consumir(url);
        try {
            return mapper.readValue(json, OmdbTemporadaDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter temporada da OMDB", e);
        }
    }
}
