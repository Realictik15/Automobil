package com.automobil.backend.repository;

import com.automobil.backend.models.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients,Long> {
}
