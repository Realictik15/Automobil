package com.automobil.backend.repository;

import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Carbody;
import com.automobil.backend.repository.query.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisRepository extends JpaRepository<Advertisments, Long> {
    @Query(
        value = Utils.FIND_ADVERTISMENT_REPORT,
        nativeQuery = true
    )
    List<Advertisments> getReportItems(@Param("vin")String vin);
    @Query(
        value = Utils.FIND_AVAILABLE_ADS,
        nativeQuery = true
    )
    List<Advertisments> getListAllAvalible();

    @Query(
        value = Utils.FIND_ADVERT_BY_CLASS,
        nativeQuery = true
    )
    List<Advertisments> getListByClass(@Param("class")Long id);
}
