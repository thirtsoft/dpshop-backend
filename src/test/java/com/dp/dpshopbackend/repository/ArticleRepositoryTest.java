package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.models.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Rollback(false)
    public void testCreateArticle() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "sacPl");

        ScategoryDto scategoryDto = new ScategoryDto();
        String scategoryLibelle = "SAC-Plage";
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art1";
        String desArtcile = "Article-1";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(desArtcile);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategoryDto(scategoryDto);


        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        assertNotNull(articleDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateArticle() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "PAPIER RAM");

        ScategoryDto scategoryDto = new ScategoryDto();
        String scategoryLibelle = "RAM";
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art2";
        String desArtcile = "PAPIER A4";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(desArtcile);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategoryDto(scategoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        String refCodeArticle = "Art22";
        String designationArticle = "PAPIER B44";
        ArticleDto articleUpdateDto = new ArticleDto();
        articleUpdateDto.setReference(refArticle);
        articleUpdateDto.setDesignation(designationArticle);
        articleUpdateDto.setScategoryDto(scategoryDto);


        articleUpdateDto.setId(1);
        ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(articleUpdateDto)));

        assertThat(articleUpdateDto.getDesignation()).isEqualTo(designationArticle);

    }

    @Test
    public void testFindById() {
        CategoryDto categoryDto = new CategoryDto(null, "tshirt", "Tshirt-Man");

        ScategoryDto scategoryDto = new ScategoryDto();
        String scategoryLibelle = "Tshirt-Mans";
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art3";
        String desArtcile = "Tshirt-Mans-Sport";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(desArtcile);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategoryDto(scategoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        Optional<Article> article = articleRepository.findById(articleDtoResult.getId());

        assertNotNull(article);

    }

    @Test
    public void testFindByReference() {
        CategoryDto categoryDto = new CategoryDto(null, "Ordi", "Ordinateurs");
        String scategoryLibelle = "HP-ProBook";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "HP";
        String designationArticle = "HP-ProBook-2020";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategoryDto(scategoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );
        String refCodeArticle = "HP";
        assertThat(articleDtoResult.getReference()).isEqualTo(refCodeArticle);
    }

    @Test
    public void testFindAll() {
        CategoryDto categoryDto = new CategoryDto(null, "Robe", "RobeElite");
        String scategoryLibelle = "RobeElite3";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "RB";
        String designationArticle = "RobeElite4";
        double priceArticle = 15000;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setPrice(priceArticle);
        articleDto.setScategoryDto(scategoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );
        CategoryDto categoryDto1 = new CategoryDto(null, "Panthalon", "Panthalon homme");
        String scategoryLibelle1 = "PanthallonHomme1";
        ScategoryDto scategoryDto1 = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle1);
        scategoryDto.setCategoryDto(categoryDto1);

        String refArticle1 = "PT";
        String designationArticle1 = "PanthallonHommeSlim";
        double priceArticle1 = 15000;
        ArticleDto articleDto1 = new ArticleDto();
        articleDto.setReference(refArticle1);
        articleDto.setDesignation(designationArticle1);
        articleDto.setPrice(priceArticle1);
        articleDto.setScategoryDto(scategoryDto1);

        ArticleDto articleDtoResult1 = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto1)
                )
        );

        CategoryDto categoryDto2 = new CategoryDto(null, "Chemise", "Chemise Femme");
        String scategoryLibelle2 = "Chemise-Femme";
        ScategoryDto scategoryDto2 = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle2);
        scategoryDto.setCategoryDto(categoryDto2);

        String refArticle2 = "PM";
        String designationArticle2 = "Chemise-FemmeSlim";
        double priceArticle2 = 15000;
        ArticleDto articleDto2 = new ArticleDto();
        articleDto.setReference(refArticle2);
        articleDto.setDesignation(designationArticle2);
        articleDto.setPrice(priceArticle2);
        articleDto.setScategoryDto(scategoryDto2);

        ArticleDto articleDtoResult2 = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto2)
                )
        );

        List<?> articles = articleRepository.findAll();

        assertThat(articles).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        CategoryDto categoryDto = new CategoryDto(null, "Chemise", "String");
        String scategoryCode = "scat5";
        String scategoryLibelle = "String-Plage";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setCode(scategoryCode);
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle2 = "Art5";
        String designationArticle2 = "String-PlageSlim";
        double priceArticle2 = 15000;
        ArticleDto articleDto2 = new ArticleDto();
        articleDto2.setReference(refArticle2);
        articleDto2.setDesignation(designationArticle2);
        articleDto2.setPrice(priceArticle2);
        articleDto2.setScategoryDto(scategoryDto);

        ArticleDto articleDtoResult2 = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto2)
                )
        );

        Long id = (long) 1;

        Optional<Article> article = articleRepository.findById(articleDtoResult2.getId());

        boolean isExistBeforeDelete = articleRepository.findById(articleDtoResult2.getId()).isPresent();

        articleRepository.deleteById(articleDtoResult2.getId());

        boolean notExistAfterDelete = articleRepository.findById(articleDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
