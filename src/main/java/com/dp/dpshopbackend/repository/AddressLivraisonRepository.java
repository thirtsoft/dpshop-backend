package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressClient;
import com.dp.dpshopbackend.models.AddressLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressLivraisonRepository extends JpaRepository<AddressLivraison, Long> {

    List<AddressLivraison> findByOrderByIdDesc();
}
