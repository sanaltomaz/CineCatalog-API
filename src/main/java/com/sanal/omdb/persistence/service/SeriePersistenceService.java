package com.sanal.omdb.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanal.omdb.models.Serie;
import com.sanal.omdb.persistence.entity.SerieEntity;
import com.sanal.omdb.persistence.mapper.SerieEntityMapper;
import com.sanal.omdb.persistence.repository.SerieRepository;

/**
 * Service responsável pela persistência de séries.
 *
 * <p>
 * Atua como fronteira entre o domínio e a camada JPA.
 * Recebe objetos de domínio já válidos e os persiste
 * como entidades JPA.
 *
 * <p>
 * Este service NÃO:
 * - Busca dados externos
 * - Cria objetos de domínio
 * - Decide fluxo de aplicação
 * - Persiste episódios ou temporadas
 */
@Service
public class SeriePersistenceService {

    private final SerieRepository serieRepository;
    private final SerieEntityMapper mapper;

    public SeriePersistenceService(
            SerieRepository serieRepository,
            SerieEntityMapper mapper
    ) {
        this.serieRepository = serieRepository;
        this.mapper = mapper;
    }

    /**
     * Persiste os metadados básicos de uma série.
     *
     * <p>
     * Pré-condições:
     * - A {@link Serie} já foi validada no domínio
     * - Não contém temporadas persistidas
     *
     * <p>
     * Pós-condições:
     * - Série persistida
     * - Retorna a entidade com ID gerado
     */
    @Transactional
    public SerieEntity salvar(Serie serie) {
        SerieEntity entity = mapper.toEntity(serie);
        return serieRepository.save(entity);
    }
}