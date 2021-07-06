package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("select count(p) from Commande p ")
    BigDecimal countNumberOfCommande();

    @Query("select sum(c.total) from Commande c where month(c.localDateTime) = month(current_date)")
    BigDecimal sumTotalOfCommandesByMonth();

    @Query("select com from Commande com where com.client.id =:clientId")
    Page<Commande> findCommandeByCustomerPageables(@Param("clientId") Long clientId, Pageable pageable);

}
