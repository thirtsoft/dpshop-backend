package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

    @Query("select (p.productName), count(p) from LigneCommande p where p.actif=1 group by (p.productId)")
    List<LigneCommande> findArticlesGroupByProductId();

    @Query("select p from LigneCommande p where p.actif=1 and p.commande.id =:num")
    List<LigneCommande> ListLigneCommandeByCommandeId(@Param("num") Long comId);

    @Query("Select  act from LigneCommande act where act.actif=1")
    List<LigneCommande> findTop200ByOrderByIdDesc();

    @Query("Select DISTINCT act from LigneCommande act where act.actif=1 ORDER BY act.id desc")
    List<LigneCommande> findAll();

}
