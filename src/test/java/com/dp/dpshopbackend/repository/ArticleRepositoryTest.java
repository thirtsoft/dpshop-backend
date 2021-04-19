package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.dto.ScategorieDto;
import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Scategorie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
        CategorieDto categorieDto = new CategorieDto(1,"sac", "sacPl");

        ScategorieDto scategorieDto = new ScategorieDto();
        String scategoryLibelle = "SAC-Plage";
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        String refArticle = "Art1";
        String desArtcile = "Article-1";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(desArtcile);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategorieDto(scategorieDto);


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
        CategorieDto categorieDto = new CategorieDto(1,"sac", "PAPIER RAM");

        ScategorieDto scategorieDto = new ScategorieDto();
        String scategoryLibelle = "RAM";
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        String refArticle = "Art2";
        String desArtcile = "PAPIER A4";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(desArtcile);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategorieDto(scategorieDto);

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
        articleUpdateDto.setScategorieDto(scategorieDto);


        articleUpdateDto.setId((long) 1);
        ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(articleUpdateDto)));

        assertThat(articleUpdateDto.getDesignation()).isEqualTo(designationArticle);

    }

    @Test
    public void testFindById() {
        CategorieDto categorieDto = new CategorieDto(1,"tshirt", "Tshirt-Man");

        ScategorieDto scategorieDto = new ScategorieDto();
        String scategoryLibelle = "Tshirt-Mans";
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        String refArticle = "Art3";
        String desArtcile = "Tshirt-Mans-Sport";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(desArtcile);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategorieDto(scategorieDto);

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
        CategorieDto categorieDto = new CategorieDto(1,"Ordi", "Ordinateurs");
        String scategoryLibelle = "HP-ProBook";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        String refArticle = "HP";
        String designationArticle = "HP-ProBook-2020";
        double priceArticle = 15000;
        int quantity = 20;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setPrice(priceArticle);
        articleDto.setQuantity(quantity);
        articleDto.setScategorieDto(scategorieDto);

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
        CategorieDto categorieDto = new CategorieDto(1,"Robe", "RobeElite");
        String scategoryLibelle = "RobeElite3";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        String refArticle = "RB";
        String designationArticle = "RobeElite4";
        double priceArticle = 15000;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setPrice(priceArticle);
        articleDto.setScategorieDto(scategorieDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );
        CategorieDto categorieDto1 = new CategorieDto(1,"Panthalon", "Panthalon homme");
        String scategoryLibelle1 = "PanthallonHomme1";
        ScategorieDto scategorieDto1 = new ScategorieDto();
        scategorieDto.setLibelle(scategoryLibelle1);
        scategorieDto.setCategorieDto(categorieDto1);

        String refArticle1 = "PT";
        String designationArticle1 = "PanthallonHommeSlim";
        double priceArticle1 = 15000;
        ArticleDto articleDto1 = new ArticleDto();
        articleDto.setReference(refArticle1);
        articleDto.setDesignation(designationArticle1);
        articleDto.setPrice(priceArticle1);
        articleDto.setScategorieDto(scategorieDto1);

        ArticleDto articleDtoResult1 = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto1)
                )
        );

        CategorieDto categorieDto2 = new CategorieDto(1,"Chemise", "Chemise Femme");
        String scategoryLibelle2 = "Chemise-Femme";
        ScategorieDto scategorieDto2 = new ScategorieDto();
        scategorieDto.setLibelle(scategoryLibelle2);
        scategorieDto.setCategorieDto(categorieDto2);

        String refArticle2 = "PM";
        String designationArticle2 = "Chemise-FemmeSlim";
        double priceArticle2 = 15000;
        ArticleDto articleDto2 = new ArticleDto();
        articleDto.setReference(refArticle2);
        articleDto.setDesignation(designationArticle2);
        articleDto.setPrice(priceArticle2);
        articleDto.setScategorieDto(scategorieDto2);

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
        CategorieDto categorieDto = new CategorieDto(1,"Chemise", "String");
        String scategoryCode = "scat5";
        String scategoryLibelle = "String-Plage";
        ScategorieDto scategorieDto = new ScategorieDto();
        scategorieDto.setCode(scategoryCode);
        scategorieDto.setLibelle(scategoryLibelle);
        scategorieDto.setCategorieDto(categorieDto);

        String refArticle2 = "Art5";
        String designationArticle2 = "String-PlageSlim";
        double priceArticle2 = 15000;
        ArticleDto articleDto2 = new ArticleDto();
        articleDto2.setReference(refArticle2);
        articleDto2.setDesignation(designationArticle2);
        articleDto2.setPrice(priceArticle2);
        articleDto2.setScategorieDto(scategorieDto);

        ArticleDto articleDtoResult2 = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto2)
                )
        );

        Long id = (long) 1;

        Optional<Article>  article = articleRepository.findById(articleDtoResult2.getId());

        boolean isExistBeforeDelete = articleRepository.findById(articleDtoResult2.getId()).isPresent();

        articleRepository.deleteById(articleDtoResult2.getId());

        boolean notExistAfterDelete = articleRepository.findById(articleDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
