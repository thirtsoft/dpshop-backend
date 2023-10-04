package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Scategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScategoryRepository extends JpaRepository<Scategory, Long> {

    @Query("Select DISTINCT act from Scategory act where act.actif=1 ORDER BY act.libelle")
    List<Scategory> findAll();
}
