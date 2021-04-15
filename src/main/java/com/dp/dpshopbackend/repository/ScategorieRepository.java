package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Scategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScategorieRepository extends JpaRepository<Scategorie, Long> {
}
