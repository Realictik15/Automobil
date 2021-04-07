package com.automobil.backend.repository;

import com.automobil.backend.models.Sizess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizessRepository extends JpaRepository<Sizess,Long> {
}
