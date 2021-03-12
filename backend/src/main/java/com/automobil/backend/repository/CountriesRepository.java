package com.automobil.backend.repository;

import com.automobil.backend.models.Carbody;
import com.automobil.backend.models.Countries;
import com.automobil.backend.repository.query.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface CountriesRepository extends JpaRepository<Countries,Long> {
    @Query(
        value = Utils.FIND_COUNTRY_BY_TILE,
        nativeQuery = true
    )
    Optional<Countries> getCarBodyByTitle(@Param("title")String title);
}
