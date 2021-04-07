package com.automobil.backend.repository;

import com.automobil.backend.models.GearBoxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GearboxesRepository extends JpaRepository<GearBoxes,Long> {
}
