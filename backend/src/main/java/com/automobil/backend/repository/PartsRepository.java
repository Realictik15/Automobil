package com.automobil.backend.repository;

import com.automobil.backend.models.Parts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepository extends JpaRepository<Parts,Long> {
}
