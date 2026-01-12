package com.sanal.omdb.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanal.omdb.persistence.entity.EpisodioEntity;

public interface EpisodioRepository extends JpaRepository<EpisodioEntity, Long> {
    
    List<EpisodioEntity> findBySerieId(Long serieId);
    
    List<EpisodioEntity> findBySerieIdAndNumeroTemporada(
        Long serieId,
        Integer numeroTemporada
    );
}
