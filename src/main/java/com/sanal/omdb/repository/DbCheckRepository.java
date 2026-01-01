package com.sanal.omdb.repository;

import com.sanal.omdb.models.DbCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbCheckRepository 
            extends JpaRepository<DbCheck, Long> {
}
