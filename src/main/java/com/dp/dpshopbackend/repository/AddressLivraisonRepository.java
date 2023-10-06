package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressLivraisonRepository extends JpaRepository<AddressLivraison, Long> {

    List<AddressLivraison> findByOrderByIdDesc();

    @Query("Select DISTINCT act from  AddressLivraison act where act.actif=1 ORDER BY act.id asc")
    List<AddressLivraison> findAll();
}
