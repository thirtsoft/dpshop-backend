package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressLivraisonRepository extends JpaRepository<AddressLivraison, Long> {
}
