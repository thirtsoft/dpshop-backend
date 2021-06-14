package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findArticleByReference(String reference);

    @Query("select p from Article p where p.scategory.id =:scat")
    List<Article> findArticleByScategory(@Param("scat") Long scatId);
}
