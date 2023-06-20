package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findArticleByReference(String reference);

    @Query("select p from Article p where p.actif=1 and p.scategory.id =:scat")
    List<Article> findArticleByScategory(@Param("scat") Long scatId);

    //@Query("select art from Article art where art.actif=1 ORDER BY art.createDate DESC LIMIT 12")
   // @Query(nativeQuery = true, value = "SELECT * FROM article* s WHERE s.actif=1 ORDER BY s.createDate DESC LIMIT 12")
   // List<Article> findTop12ByOrderByCreateDateDesc();
    List<Article> findFirst12AndActifIsTrueByOrderByCreateDateDesc();

 //   @Query(value="select * from article where actif=1 ORDER BY createDate DESC limit 12 ", nativeQuery=true)
 //   @Query(value = "SELECT * FROM article ORDER BY createDate DESC LIMIT 12", nativeQuery = true)
 //   List<Article> ListDes12ArticleOrderByCreatedDate();

    @Query(value = "select * from shopmania.article where actif=1 order by create_date desc limit 12", nativeQuery = true)
    List<Article> findTop12ByOrderByCreateDateDesc();


   // List<Article>  findTopByActifIsTrue;

    List<Article> findByOrderByIdDesc();

    @Query("select art from Article art where art.price like :price GROUP BY (art.price, art.id) ")
    List<Article> findArticleGroupByPrice(@Param("price") double price);

    @Query("select p from Article p where p.price between :min and :max")
    List<Article> findListArticleByPriceMinMax(@Param("min") double min, @Param("max") double max);

    @Query("select art from Article art where art.actif=1 and art.selected = true")
    List<Article> findArticleBySelected();

    @Query("select art from Article art where art.actif=1 and art.designation like :x")
    List<Article> findArticleByKeyword(@Param("x") String mc);

    @Query("select p from Article p")
    Page<Article> findArticle(Pageable pageable);

    @Query("select p from Article p where p.actif=1 and p.scategory.id =:scat")
    Page<Article> findArticleByScategoryPageables(@Param("scat") Long scatId, Pageable pageable);

    @Query("select art from Article art where art.price like :price GROUP BY (art.price, art.id) ")
    Page<Article> findArticlePageableGroupByPrice(@Param("price") double price, Pageable pageable);

    @Query("select count(p) from Article p where p.scategory.id =:subcat ")
    BigDecimal countNumberOfArticleInSubCategory(@Param("subcat") Long scatId);

    @Query("Select DISTINCT act from  Article act where act.actif=1 ORDER BY act.designation")
    List<Article> findAll();

}
