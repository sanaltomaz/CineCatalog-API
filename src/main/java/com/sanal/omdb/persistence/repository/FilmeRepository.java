package com.sanal.omdb.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanal.omdb.persistence.entity.FilmeEntity;

public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
}
