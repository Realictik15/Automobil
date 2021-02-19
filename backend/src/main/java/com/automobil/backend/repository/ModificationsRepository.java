package com.automobil.backend.repository;

import com.automobil.backend.models.Modifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModificationsRepository extends JpaRepository<Modifications,Long> {
}
