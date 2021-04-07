package com.automobil.backend.repository;

import com.automobil.backend.models.Generations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationsRepository extends JpaRepository<Generations,Long> {
}
