package com.automobil.backend.repository;

import com.automobil.backend.models.Advertisments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisRepository extends JpaRepository<Advertisments, Long> {
}
