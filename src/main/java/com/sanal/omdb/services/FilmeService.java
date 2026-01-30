package com.sanal.omdb.services;

import org.springframework.stereotype.Service;

import com.sanal.omdb.dto.omdb.OmdbFilmeDto;
import com.sanal.omdb.models.Filme;
import com.sanal.omdb.omdb.OmdbClient;

/**
 * Service de aplicação responsável pelo caso de uso
 * de obtenção de filmes.
 *
 * Orquestra a integração com a OMDB e a conversão
 * para o domínio, sem aplicar regras de negócio.
 */
@Service
public class FilmeService {

    private final OmdbClient omdbClient;
    private final OmdbDomainFactory factory;

    public FilmeService(
            OmdbClient omdbClient,
            OmdbDomainFactory factory
    ) {
        this.omdbClient = omdbClient;
        this.factory = factory;
    }

    public Filme buscarPorNome(String nome) {
        OmdbFilmeDto dto = omdbClient.buscarFilme(nome);
        return factory.criarFilme(dto);
    }
}
