package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Optional<Categorie> findCategorieByDesignation(String designation);
}
