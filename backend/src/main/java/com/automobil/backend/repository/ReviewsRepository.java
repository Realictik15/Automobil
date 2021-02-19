package com.automobil.backend.repository;

import com.automobil.backend.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
}
