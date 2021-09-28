package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    @Query("select count(p) from Fournisseur p ")
    BigDecimal countNumberOfFournisseur();
}
