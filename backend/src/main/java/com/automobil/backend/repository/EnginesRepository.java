package com.automobil.backend.repository;

import com.automobil.backend.models.Engines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnginesRepository extends JpaRepository<Engines,Long> {
}
