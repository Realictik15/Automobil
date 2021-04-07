package com.automobil.backend.repository;

import com.automobil.backend.models.Transmissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionsRepository extends JpaRepository<Transmissions,Long> {
}
