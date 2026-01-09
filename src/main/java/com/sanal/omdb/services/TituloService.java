package com.sanal.omdb.services;

import org.springframework.stereotype.Service;

import com.sanal.omdb.models.Titulo;
import com.sanal.omdb.omdb.OmdbClient;

@Service
public class TituloService {

    private final OmdbClient omdbClient;
    private final TituloFactory tituloFactory = new TituloFactory();

    public TituloService(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    /**
     * Busca um título pelo nome e retorna o objeto de domínio correspondente.
     *
     * Responsabilidade:
     * - Orquestrar a busca de dados externos
     * - Delegar a criação do domínio para a factory
     *
     * Não faz:
     * - Chamada HTTP direta
     * - Conversão de JSON
     * - Regras de apresentação
     */
    public Titulo buscarPorNome(String nome) {
        return null;
    }
}
