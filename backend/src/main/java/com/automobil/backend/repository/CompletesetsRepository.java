package com.automobil.backend.repository;

import com.automobil.backend.models.CompleteSets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletesetsRepository extends JpaRepository<CompleteSets,Long> {
}
