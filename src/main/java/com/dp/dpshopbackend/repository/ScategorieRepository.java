package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Scategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScategorieRepository extends JpaRepository<Scategorie, Long> {

    Optional<Scategorie> findScategorieByLibelle(String libelle);
}
