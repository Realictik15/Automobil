package com.automobil.backend.repository;

import com.automobil.backend.models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CountriesRepository extends JpaRepository<Countries,Long> {
}
