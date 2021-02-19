package com.automobil.backend.repository;

import com.automobil.backend.models.Advertisments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisRepository extends JpaRepository<Advertisments, Long> {
}
