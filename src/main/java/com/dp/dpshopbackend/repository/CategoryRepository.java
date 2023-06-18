package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressLivraison;
import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategorieByDesignation(String designation);

    List<Category> findByOrderByIdDesc();

    @Query("Select DISTINCT act from  Category act where act.actif=1 ORDER BY act.designation asc ")
    List<Category> findAll();


}
