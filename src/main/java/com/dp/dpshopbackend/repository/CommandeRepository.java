package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("select count(p) from Commande p ")
    BigDecimal countNumberOfCommande();

    @Query("select count(c) from Commande c where month(c.dateCommande) = month(current_date)")
    BigDecimal countNumberOfOrdersInMonth();

    @Query("select count(c) from Commande c where c.statusCommande = 'ENCOURS' ")
    BigDecimal countNumberOfOrdersByStatusPending();

    @Query("select sum(c.totalCommande) from Commande c where month(c.dateCommande) = month(current_date)")
    BigDecimal sumTotalOfCommandesByMonth();

    @Query("select sum(c.totalCommande) from Commande c where month(c.dateCommande) = month(current_date)")
    BigDecimal sumTotaleOfCommandeByMonth();

    @Query("select sum(c.totalCommande) from Commande c where year(c.dateCommande) = year(current_date)")
    BigDecimal sumTotalOfCommandesByYear();


   /* @Query("select com from Commande com where com.client.id =:clientId")
    Page<Commande> findCommandeByCustomerPageables(@Param("clientId") Long clientId, Pageable pageable);
*/
   /* @Query("select com from Commande com where com.utilisateur.id =:userId")
    List<Commande> findListOrderByUserId(@Param("userId") Long id);*/

    @Query("select count(c) from Commande c where c.statusCommande = 'ENCOURS' ")
    List<Commande> findListOrderByStatusPending();

    List<Commande> findByOrderByIdDesc();


    //  List<Commande> findAllByUtilisateurOrderByCreatedDateDesc(Utilisateur utilisateur);

    @Query("select EXTRACT(month from(c.dateCommande)), count(c) from Commande c group by EXTRACT(month from(c.dateCommande))")
    List<?> countNumberOfCommandeByMonth();

    @Query("select EXTRACT(month from(c.dateCommande)), sum(c.totalCommande) from Commande c group by EXTRACT(month from(c.dateCommande))")
    List<?> sumTotalOfCommandeByMonth();

    @Query("select EXTRACT(year from(v.dateCommande)), sum(v.totalCommande) from Commande v group by EXTRACT(year from(v.dateCommande))")
    List<?> sumTotalOfCommandeByYears();

   /* @Query("select p from Commande p where p.utilisateur.id =:user")
    List<Commande> ListCommandeByCustomerId(@Param("user") Long userId);*/

   /* @Query("select com from Commande com where com.utilisateur.id =:userId")
    Page<Commande> findCommandeByUtilisateurPageables(@Param("userId") Long userId, Pageable pageable);*/

}
