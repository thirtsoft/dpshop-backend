package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.models.Fournisseur;
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
public class FournisseurRepositoryTest {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Test
    @Rollback(false)
    public void testCreateFournisseur() {
        CategoryDto categoryDto = new CategoryDto(null, "Chemise", "Chemise Femme");
        String libelle = "ChemiseFemmeXL";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setLibelle(libelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art1";
        String designationArticle = "Article-1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setScategoryDto(scategoryDto);

        FournisseurDto fournisseurDto = new FournisseurDto();
        String reference = "Four-1";
        String firstName = "Fourni-1";
        String lastName = "Fourni-1";
        String address = "Dakar";
        String email = "thirdiallo@gmail.com";
        String telephoneFournisseur = "779440310";
        fournisseurDto.setReference(reference);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setAddress(address);
        fournisseurDto.setEmail(email);
        fournisseurDto.setTelephoneFournisseur(telephoneFournisseur);
        fournisseurDto.setArticleDto(articleDto);

        FournisseurDto fournisseurDtoResult = FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto)
                )
        );

        assertNotNull(fournisseurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateFournisseur() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "PAPIER RAM");
        String libelle = "PAPIER_RAM_DUR";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setLibelle(libelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art1";
        String designationArticle = "Article-1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setScategoryDto(scategoryDto);

        FournisseurDto fournisseurDto = new FournisseurDto();
        String reference = "Four-1";
        String firstName = "Fourni-1";
        String lastName = "Fourni-1";
        String email = "thirdiallo@gmail.com";
        String telephoneFournisseur = "779440310";
        fournisseurDto.setReference(reference);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setTelephoneFournisseur(telephoneFournisseur);
        fournisseurDto.setArticleDto(articleDto);

        FournisseurDto fournisseurDtoResult = FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto)
                )
        );
        String refFournisseur = "Diallo";
        FournisseurDto fournisseurUpdateDto = new FournisseurDto();
        fournisseurUpdateDto.setReference(refFournisseur);
        fournisseurUpdateDto.setId(1);
        FournisseurDto.fromEntityToDto(fournisseurRepository.save(FournisseurDto.fromDtoToEntity(fournisseurUpdateDto)));

        assertThat(fournisseurUpdateDto.getReference()).isEqualTo(refFournisseur);

    }

    @Test
    public void testFindById() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "sac a mai");
        String scategoryLibelle = "sacFille";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art1";
        String designationArticle = "Article-1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setScategoryDto(scategoryDto);

        FournisseurDto fournisseurDto = new FournisseurDto();
        String reference = "Four-1";
        String firstName = "Fourni-1";
        String lastName = "Fourni-1";
        String email = "thirdiallo@gmail.com";
        String telephoneFournisseur = "779440310";
        fournisseurDto.setReference(reference);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setTelephoneFournisseur(telephoneFournisseur);
        fournisseurDto.setArticleDto(articleDto);

        FournisseurDto fournisseurDtoResult = FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto)
                )
        );

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurDtoResult.getId());

        boolean isCategory = fournisseurRepository.findById(fournisseurDtoResult.getId()).isPresent();

        assertTrue(isCategory);

    }

    @Test
    public void testFindAll() {
        CategoryDto categoryDto = new CategoryDto(null, "sac", "sac a mai");

        String scategoryLibelle = "sacFille";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art1";
        String designationArticle = "Article-1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setScategoryDto(scategoryDto);

        FournisseurDto fournisseurDto = new FournisseurDto();
        String reference = "Four-1";
        String firstName = "Fourni-1";
        String lastName = "Fourni-1";
        String email = "thirdiallo@gmail.com";
        String telephoneFournisseur = "779440310";
        fournisseurDto.setReference(reference);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setTelephoneFournisseur(telephoneFournisseur);
        fournisseurDto.setArticleDto(articleDto);

        FournisseurDto fournisseurDtoResult = FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto)
                )
        );

        CategoryDto categoryDto1 = new CategoryDto(null, "Panthalon", "Panthalon homme");
        String scategoryLibelle1 = "sacFille";
        ScategoryDto scategoryDto1 = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle1);
        scategoryDto.setCategoryDto(categoryDto1);

        String designationArticle1 = "Article-1";
        ArticleDto articleDto1 = new ArticleDto();
        articleDto.setDesignation(designationArticle1);
        articleDto.setScategoryDto(scategoryDto1);

        FournisseurDto fournisseurDto1 = new FournisseurDto();
        String reference1 = "Four-1";
        String firstName1 = "Fourni-1";
        String lastName1 = "Fourni-1";
        fournisseurDto.setReference(reference1);
        fournisseurDto.setFirstName(firstName1);
        fournisseurDto.setLastName(lastName1);
        fournisseurDto.setArticleDto(articleDto1);

        FournisseurDto fournisseurDtoResult1 = FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto1)
                )
        );

        CategoryDto categoryDto2 = new CategoryDto(null, "Chemise", "Chemise Femme");
        String scategoryLibelle2 = "sacFille";
        ScategoryDto scategoryDto2 = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle2);
        scategoryDto.setCategoryDto(categoryDto2);

        String designationArticle2 = "Article-1";
        ArticleDto articleDto2 = new ArticleDto();
        articleDto.setDesignation(designationArticle2);
        articleDto.setScategoryDto(scategoryDto1);

        FournisseurDto fournisseurDto2 = new FournisseurDto();
        String reference2 = "Four-1";
        String firstName2 = "Fourni-1";
        String lastName2 = "Fourni-1";
        fournisseurDto.setReference(reference2);
        fournisseurDto.setFirstName(firstName2);
        fournisseurDto.setLastName(lastName1);
        fournisseurDto.setArticleDto(articleDto2);

        FournisseurDto fournisseurDtoResult2 = FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto2)
                )
        );

        List<?> fournisseurs = fournisseurRepository.findAll();

        assertThat(fournisseurs).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        CategoryDto categoryDto = new CategoryDto(null, "Chemise", "Chemise Femme");
        String scategoryLibelle = "sacFille";
        ScategoryDto scategoryDto = new ScategoryDto();
        scategoryDto.setLibelle(scategoryLibelle);
        scategoryDto.setCategoryDto(categoryDto);

        String refArticle = "Art1";
        String designationArticle = "Article-1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArticle);
        articleDto.setScategoryDto(scategoryDto);

        FournisseurDto fournisseurDto = new FournisseurDto();
        String reference = "Four-1";
        String firstName = "Fourni-1";
        String lastName = "Fourni-1";
        String email = "thirdiallo@gmail.com";
        String telephoneFournisseur = "779440310";
        fournisseurDto.setReference(reference);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setTelephoneFournisseur(telephoneFournisseur);
        fournisseurDto.setArticleDto(articleDto);

        FournisseurDto fournisseurDtoResult2 = FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto)
                )
        );

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurDtoResult2.getId());

        boolean isExistBeforeDelete = fournisseurRepository.findById(fournisseurDtoResult2.getId()).isPresent();

        fournisseurRepository.deleteById(fournisseurDtoResult2.getId());

        boolean notExistAfterDelete = fournisseurRepository.findById(fournisseurDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
