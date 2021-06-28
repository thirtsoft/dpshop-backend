package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("select com from Commande com where com.client.id =:clientId")
    Page<Commande> findCommandeByCustomerPageables(@Param("clientId") Long clientId, Pageable pageable);

}
