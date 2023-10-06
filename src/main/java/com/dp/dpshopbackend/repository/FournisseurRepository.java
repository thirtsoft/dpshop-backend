package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    @Query("select count(p) from Fournisseur p where p.actif=1")
    BigDecimal countNumberOfFournisseur();

    @Query("Select DISTINCT act from Fournisseur act where act.actif=1 ORDER BY act.firstName")
    List<Fournisseur> findAll();
}
