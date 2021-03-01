package com.automobil.backend.repository;

import com.automobil.backend.models.Models;
import com.automobil.backend.repository.query.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelsRepository extends JpaRepository<Models,Long> {
    @Query(
        value = Utils.FIND_MODEL_BY_NAME,
        nativeQuery = true
    )
    Models getModelsByTitle(@Param("title")String title);
}
