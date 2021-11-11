package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Country;
import com.dp.dpshopbackend.models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

    @Query("select (p.productName), count(p) from LigneCommande p group by (p.productId)")
    List<LigneCommande> findArticlesGroupByProductId();

    List<LigneCommande> findByOrderByIdDesc();

}
