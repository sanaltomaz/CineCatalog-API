package com.sanal.omdb.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanal.omdb.persistence.entity.TituloEntity;

public interface TituloRepository extends JpaRepository<TituloEntity, Long> {
}
