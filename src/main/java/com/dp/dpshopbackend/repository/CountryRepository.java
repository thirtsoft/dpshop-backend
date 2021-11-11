package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByOrderByIdDesc();

}
