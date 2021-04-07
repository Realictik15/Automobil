package com.automobil.backend.repository;

import com.automobil.backend.models.Carbody;
import com.automobil.backend.repository.query.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarbodyRepository extends JpaRepository<Carbody,Long> {
    @Query(
        value = Utils.FIND_CARBODY_BY_NAME,
        nativeQuery = true
    )
    Optional<Carbody> getCarBodyByTitle(@Param("title")String title);
}

