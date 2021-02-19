package com.automobil.backend.repository;

import com.automobil.backend.models.Bodies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodiesRepository extends JpaRepository<Bodies,Long> {
}
