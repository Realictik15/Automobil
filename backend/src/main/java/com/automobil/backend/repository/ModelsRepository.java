package com.automobil.backend.repository;

import com.automobil.backend.models.Models;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelsRepository extends JpaRepository<Models,Long> {
}
