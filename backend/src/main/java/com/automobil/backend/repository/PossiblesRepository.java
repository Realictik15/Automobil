package com.automobil.backend.repository;

import com.automobil.backend.models.Possibles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PossiblesRepository extends JpaRepository<Possibles,Long> {
}
