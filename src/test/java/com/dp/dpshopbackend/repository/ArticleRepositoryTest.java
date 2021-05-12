package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Scategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ScategoryRepository scategoryRepository;

    @Test
    @Rollback(false)
    public void testCreateArticle() {

        Long scatId = (long) 1;
        Scategory scategory = scategoryRepository.findById(scatId).orElse(null);

        Article article = new Article(1L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategory);

        Article articleResult = articleRepository.save(article);

        assertNotNull(articleResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateArticle() {
        Long scatId = (long) 1;
        Scategory scategory = scategoryRepository.findById(scatId).orElse(null);

        Article article = new Article(1L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategory);

        articleRepository.save(article);

        String refArticle = "Art2";
        String desArtcile = "PAPIER A4";
        double priceArticle = 1700.0;
        int quantity = 20;

        article.setId(3L);
        article.setReference(refArticle);
        article.setDesignation(desArtcile);
        article.setPrice(priceArticle);
        article.setQuantity(quantity);

        Article articleUpdate = articleRepository.save(article);

        assertThat(articleUpdate.getReference()).isEqualTo(refArticle);
        assertThat(articleUpdate.getDesignation()).isEqualTo(desArtcile);
        assertThat(articleUpdate.getDescription()).isEqualTo(article.getDescription());
        assertThat(articleUpdate.getPrice()).isEqualTo(article.getPrice());

    }

    @Test
    public void testFindById() {
        Long catId = (long) 1;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        Article article = new Article((long) 1, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategory);

        Article articleResult = articleRepository.save(article);

        boolean isExistArticle = articleRepository.findById(articleResult.getId()).isPresent();

        assertTrue(isExistArticle);

    }

    @Test
    public void testFindByReference() {
        Long catId = 1L;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        Article article = new Article(1L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategory);

        Article articleResult = articleRepository.save(article);

        String refArticle = "prod1";

        assertThat(articleResult.getReference()).isEqualTo(refArticle);

    }

    @Test
    public void testFindAll() {
        Long catId = 1L;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        Article article = new Article(1L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategory);
        articleRepository.save(article);

        Article article1 = new Article(2L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategory);
        articleRepository.save(article1);

        List<Article> articles = articleRepository.findAll();

        assertThat(articles).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {

        Long catId = (long) 1;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        Article article = new Article(1L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategory);

        Article articleDelete = articleRepository.save(article);

        boolean isExistBeforeDelete = articleRepository.findById(articleDelete.getId()).isPresent();

        articleRepository.deleteById(articleDelete.getId());

        boolean notExistAfterDelete = articleRepository.findById(articleDelete.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
