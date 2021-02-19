package com.automobil.backend.repository;

import com.automobil.backend.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages,Long> {
}
