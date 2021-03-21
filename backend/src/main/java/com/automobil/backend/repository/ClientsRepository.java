package com.automobil.backend.repository;

import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;
import com.automobil.backend.repository.query.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Clients,Long> {
    @Query(
        value = Utils.FIND_CLIENT_BY_LOGIN,
        nativeQuery = true
    )
    Clients getByLogin(@Param("login")String login);
}
