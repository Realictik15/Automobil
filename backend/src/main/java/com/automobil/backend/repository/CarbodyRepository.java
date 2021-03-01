package com.automobil.backend.repository;

import com.automobil.backend.models.Carbody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbodyRepository extends JpaRepository<Carbody,Long> {
}
