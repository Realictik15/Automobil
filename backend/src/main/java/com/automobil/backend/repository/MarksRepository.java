package com.automobil.backend.repository;

import com.automobil.backend.models.Marks;
import com.automobil.backend.repository.query.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Long> {
   @Query(
       value = Utils.FIND_MARK_BY_NAME,
       nativeQuery = true
   )
    Marks getMarkByTitle(@Param("title")String title);
}
