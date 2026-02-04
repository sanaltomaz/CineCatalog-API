package com.sanal.omdb.persistence.service;

import org.springframework.stereotype.Service;

import com.sanal.omdb.models.Filme;
import com.sanal.omdb.persistence.entity.FilmeEntity;
import com.sanal.omdb.persistence.mapper.FilmeEntityMapper;
import com.sanal.omdb.persistence.repository.FilmeRepository;

import jakarta.transaction.Transactional;

/**
 * Service responsável pela persistência de filmes.
 *
 * <p>
 * Atua como fronteira entre o domínio e a camada JPA,
 * persistindo apenas os metadados do filme.
 *
 * <p>
 * Pré-condições assumidas:
 * - O {@link Filme} já foi validado pelo domínio
 *
 * <p>
 * Este service NÃO:
 * - Busca dados externos
 * - Cria objetos de domínio
 * - Executa regras de negócio
 * - Coordena fluxo de aplicação
 */
@Service
public class FilmePersistenceService {
    
    private final FilmeRepository filmeRepository;
    private final FilmeEntityMapper mapper;

    public FilmePersistenceService(
            FilmeRepository filmeRepository,
            FilmeEntityMapper mapper
    ) {
        this.filmeRepository = filmeRepository;
        this.mapper = mapper;
    }

    /**
     * Persiste um filme no banco de dados.
     *
     * <p>
     * Pós-condições:
     * - Filme persistido
     * - Retorna a entidade com ID gerado
     */
    @Transactional
    public FilmeEntity salvar(Filme filme) {
        FilmeEntity entity = mapper.toEntity(filme);
        return filmeRepository.save(entity);
    }
}
