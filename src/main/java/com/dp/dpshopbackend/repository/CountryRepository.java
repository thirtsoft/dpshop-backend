package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByOrderByIdDesc();

    @Query("Select DISTINCT act from Country act where act.actif=1 ORDER BY act.name")
    List<Country> findAll();

}
