package com.automobil.backend.repository;

import com.automobil.backend.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
}
