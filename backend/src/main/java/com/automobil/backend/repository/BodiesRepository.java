package com.automobil.backend.repository;

import com.automobil.backend.models.Bodies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodiesRepository extends JpaRepository<Bodies,Long> {
}
