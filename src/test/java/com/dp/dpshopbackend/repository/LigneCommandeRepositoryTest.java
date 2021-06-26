package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.LigneCommande;
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

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Rollback(false)
    public void testCreateLigneCommande() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        LigneCommande ligneCommande = new LigneCommande(1L, 1234L, 12, 4500.50, commande, article);

        LigneCommande ligneCommandeResult = ligneCommandeRepository.save(ligneCommande);

        assertNotNull(ligneCommandeResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateLigneCommande() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        LigneCommande ligneCommande = new LigneCommande(1L, 1234L, 12, 4500.50, commande, article);
        ligneCommandeRepository.save(ligneCommande);

        long numero = 120;
        double price = 12500;
        ligneCommande.setId(2L);
        ligneCommande.setNumero(numero);
        ligneCommande.setPrice(price);

        LigneCommande ligneCommandeUpdate = ligneCommandeRepository.save(ligneCommande);

        assertThat(ligneCommandeUpdate.getNumero()).isEqualTo(numero);
        assertThat(ligneCommandeUpdate.getPrice()).isEqualTo(price);
        assertThat(ligneCommandeUpdate.getQuantity()).isEqualTo(ligneCommande.getQuantity());

    }

    @Test
    public void testFindById() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        LigneCommande ligneCommande = new LigneCommande(1L, 1234L, 12, 4500.50, commande, article);

        LigneCommande ligneCommandeResult = ligneCommandeRepository.save(ligneCommande);

        boolean isExistLigneCommande = ligneCommandeRepository.findById(ligneCommandeResult.getId()).isPresent();

        assertTrue(isExistLigneCommande);

    }

    @Test
    public void testFindAll() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        LigneCommande ligneCommande = new LigneCommande(1L, 1234L, 12, 4500.50, commande, article);
        ligneCommandeRepository.save(ligneCommande);

        LigneCommande ligneCommande1 = new LigneCommande(2L, 1234L, 10, 4000.50, commande, article);
        ligneCommandeRepository.save(ligneCommande1);

        List<LigneCommande> ligneCommandes = ligneCommandeRepository.findAll();

        assertThat(ligneCommandes).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        LigneCommande ligneCommande = new LigneCommande(1L, 1234L, 12, 4500.50, commande, article);

        LigneCommande ligneCommandeResult = ligneCommandeRepository.save(ligneCommande);

        boolean isExistBeforeDelete = ligneCommandeRepository.findById(ligneCommandeResult.getId()).isPresent();

        ligneCommandeRepository.deleteById(ligneCommandeResult.getId());

        boolean notExistAfterDelete = ligneCommandeRepository.findById(ligneCommandeResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
