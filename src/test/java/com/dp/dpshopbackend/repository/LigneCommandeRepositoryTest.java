package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.dto.LigneCommandeDto;
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
public class LigneCommandeRepositoryTest {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Test
    @Rollback(false)
    public void testCreateLigneCommande() {
        String reference = "Art1";
        String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designationArticle);

        String referenceCommmande = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(referenceCommmande);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);

        long numero = 120;
        int quantity = 5;
        double price = 12500;
        LigneCommandeDto ligneCommandeDto = new LigneCommandeDto();
        ligneCommandeDto.setNumero(numero);
        ligneCommandeDto.setQuantity(quantity);
        ligneCommandeDto.setPrice(price);
        ligneCommandeDto.setArticleDto(articleDto);
        ligneCommandeDto.setCommandeDto(commandeDto);

        LigneCommandeDto ligneCommandeDtoResult = LigneCommandeDto.fromEntityToDto(
                ligneCommandeRepository.save(
                        LigneCommandeDto.fromDtoToEntity(ligneCommandeDto)
                )
        );

        assertNotNull(ligneCommandeDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateLigneCommande() {
        String reference = "Art1";
        String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designationArticle);

        String referenceCommmande = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(referenceCommmande);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);

        long numero = 120;
        int quantity = 5;
        double price = 12500;
        LigneCommandeDto ligneCommandeDto = new LigneCommandeDto();
        ligneCommandeDto.setNumero(numero);
        ligneCommandeDto.setQuantity(quantity);
        ligneCommandeDto.setPrice(price);
        ligneCommandeDto.setArticleDto(articleDto);
        ligneCommandeDto.setCommandeDto(commandeDto);

        LigneCommandeDto ligneCommandeDtoResult = LigneCommandeDto.fromEntityToDto(
                ligneCommandeRepository.save(
                        LigneCommandeDto.fromDtoToEntity(ligneCommandeDto)
                )
        );

        long numeroLigneCommande = 122;
        LigneCommandeDto ligneCommandeUpdateDto = new LigneCommandeDto();
        ligneCommandeUpdateDto.setNumero(numeroLigneCommande);
        ligneCommandeUpdateDto.setId(1);
        LigneCommandeDto.fromEntityToDto(ligneCommandeRepository.save(LigneCommandeDto.fromDtoToEntity(ligneCommandeUpdateDto)));

        assertThat(ligneCommandeUpdateDto.getNumero()).isEqualTo(numeroLigneCommande);

    }

    @Test
    public void testFindById() {
        String reference = "Art1";
        String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designationArticle);

        String referenceCommmande = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(referenceCommmande);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);

        long numero = 120;
        int quantity = 5;
        double price = 12500;
        LigneCommandeDto ligneCommandeDto = new LigneCommandeDto();
        ligneCommandeDto.setNumero(numero);
        ligneCommandeDto.setQuantity(quantity);
        ligneCommandeDto.setPrice(price);
        ligneCommandeDto.setArticleDto(articleDto);
        ligneCommandeDto.setCommandeDto(commandeDto);

        LigneCommandeDto ligneCommandeDtoResult = LigneCommandeDto.fromEntityToDto(
                ligneCommandeRepository.save(
                        LigneCommandeDto.fromDtoToEntity(ligneCommandeDto)
                )
        );

        boolean isExistLigneCommande = ligneCommandeRepository.findById(ligneCommandeDtoResult.getId()).isPresent();

        assertTrue(isExistLigneCommande);

    }

    @Test
    public void testFindAll() {
        String reference = "Art1";
        String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designationArticle);

        String referenceCommmande = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(referenceCommmande);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);

        long numero = 120;
        int quantity = 5;
        double price = 12500;
        LigneCommandeDto ligneCommandeDto = new LigneCommandeDto();
        ligneCommandeDto.setNumero(numero);
        ligneCommandeDto.setQuantity(quantity);
        ligneCommandeDto.setPrice(price);
        ligneCommandeDto.setArticleDto(articleDto);
        ligneCommandeDto.setCommandeDto(commandeDto);

        LigneCommandeDto ligneCommandeDtoResult = LigneCommandeDto.fromEntityToDto(
                ligneCommandeRepository.save(
                        LigneCommandeDto.fromDtoToEntity(ligneCommandeDto)
                )
        );

        long numeroLigneCommande = 122;
        LigneCommandeDto ligneCommandeDto1 = new LigneCommandeDto();
        ligneCommandeDto1.setNumero(numeroLigneCommande);

        LigneCommandeDto ligneCommandeDtoResult1 = LigneCommandeDto.fromEntityToDto(
                ligneCommandeRepository.save(
                        LigneCommandeDto.fromDtoToEntity(ligneCommandeDto1)
                )
        );

        List<?> ligneCommandes = ligneCommandeRepository.findAll();

        assertThat(ligneCommandes).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String reference = "Art1";
        String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designationArticle);

        String referenceCommmande = "Com120";
        String numeroCommande = "Com120";
        double total = 30000;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setReference(referenceCommmande);
        commandeDto.setNumeroCommande(numeroCommande);
        commandeDto.setTotal(total);

        long numero = 120;
        int quantity = 5;
        double price = 12500;
        LigneCommandeDto ligneCommandeDto = new LigneCommandeDto();
        ligneCommandeDto.setNumero(numero);
        ligneCommandeDto.setQuantity(quantity);
        ligneCommandeDto.setPrice(price);
        ligneCommandeDto.setArticleDto(articleDto);
        ligneCommandeDto.setCommandeDto(commandeDto);

        LigneCommandeDto ligneCommandeDtoResult = LigneCommandeDto.fromEntityToDto(
                ligneCommandeRepository.save(
                        LigneCommandeDto.fromDtoToEntity(ligneCommandeDto)
                )
        );

        boolean isExistBeforeDelete = ligneCommandeRepository.findById(ligneCommandeDtoResult.getId()).isPresent();

        ligneCommandeRepository.deleteById(ligneCommandeDtoResult.getId());

        boolean notExistAfterDelete = ligneCommandeRepository.findById(ligneCommandeDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
