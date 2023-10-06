package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("select count(p) from Commande p where p.actif=1")
    BigDecimal countNumberOfCommande();

    @Query("select count(c) from Commande c where c.actif=1 and month(c.dateCommande) = month(current_date)")
    BigDecimal countNumberOfOrdersInMonth();

    @Query("select count(c) from Commande c where c.actif=1 and c.status = 'ENCOURS' ")
    BigDecimal countNumberOfOrdersByStatusPending();

    @Query("select sum(c.totalCommande) from Commande c where c.actif=1 and  c.dateCommande > current_date")
    BigDecimal sumTotalOfCommandeByDay();

    @Query("select sum(c.totalCommande) from Commande c where c.actif=1 and month(c.dateCommande) = month(current_date)")
    BigDecimal sumTotalOfCommandesByMonth();

    @Query("select sum(c.totalCommande) from Commande c where c.actif=1 and month(c.dateCommande) = month(current_date)")
    BigDecimal sumTotaleOfCommandeByMonth();

    @Query("select sum(c.totalCommande) from Commande c where c.actif=1 and year(c.dateCommande) = year(current_date)")
    BigDecimal sumTotalOfCommandesByYear();

    @Query("select c from Commande c where c.actif=1 and c.status = 'ENCOURS' order by c.id Desc ")
    List<Commande> findListOrderByStatusPending();

    @Query("select c from Commande c where c.actif=1 and c.status = 'PAYEE' order by c.id Desc ")
    List<Commande> findListOrderByStatusPayed();

    @Query("select EXTRACT(day from(c.dateCommande)), count(c) from Commande c where c.actif=1 group by EXTRACT(day from(c.dateCommande))")
    List<?> countNumberOfCommandeByDay();

    @Query("select EXTRACT(month from(c.dateCommande)), count(c) from Commande c where c.actif=1 group by EXTRACT(month from(c.dateCommande))")
    List<?> countNumberOfCommandeByMonth();

    @Query("select EXTRACT(month from(c.dateCommande)), sum(c.totalCommande) from Commande c where c.actif=1 group by EXTRACT(month from(c.dateCommande))")
    List<?> sumTotalOfCommandeByMonth();

    @Query("select EXTRACT(year from(v.dateCommande)), sum(v.totalCommande) from Commande v where v.actif=1 group by EXTRACT(year from(v.dateCommande))")
    List<?> sumTotalOfCommandeByYears();

    @Query("select p from Commande p where p.actif=1 and p.utilisateur.id =:user order by p.id Desc")
    List<Commande> ListCommandeByCustomerId(@Param("user") Long userId);

    @Query("select p from Commande p where p.actif=1 and p.shippingAddress.id =:addLivraison order by p.id Desc")
    List<Commande> ListCommandeByAddressLivraisonId(@Param("addLivraison") Long addLivraison);

    @Query("select p from Commande p where p.actif=1 and p.billingAddress.id =:addAchat order by p.id Desc")
    List<Commande> ListCommandeByAddressAchatId(@Param("addAchat") Long addAchat);

    @Query("select com from Commande com where com.actif=1 and com.utilisateur.id =:userId")
    Page<Commande> findCommandeByUtilisateurPageables(@Param("userId") Long userId, Pageable pageable);

    @Query("Select DISTINCT act from Commande act where act.actif=1 ORDER BY act.numeroCommande desc")
    List<Commande> findAll();

}
