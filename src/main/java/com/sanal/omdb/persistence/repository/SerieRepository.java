package com.sanal.omdb.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanal.omdb.persistence.entity.SerieEntity;

public interface SerieRepository extends JpaRepository<SerieEntity, Long> {
}
