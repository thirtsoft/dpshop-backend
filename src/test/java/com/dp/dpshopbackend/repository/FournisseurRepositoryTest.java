package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Fournisseur;
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
public class FournisseurRepositoryTest {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Rollback(false)
    public void testCreateFournisseur() {
        Fournisseur f1 = new Fournisseur((long) 1, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1");
        Fournisseur fournisseurResult = fournisseurRepository.save(f1);
        assertNotNull(fournisseurResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateFournisseur() {
        Fournisseur f1 = new Fournisseur(1L, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1");
        fournisseurRepository.save(f1);

        String refFournisseur = "fournisseur";
        String firstName = "Bobo Diallo";

        f1.setId(2L);
        f1.setReference(refFournisseur);
        f1.setFirstName(firstName);

        Fournisseur fournisseurUpdate = fournisseurRepository.save(f1);

        assertThat(fournisseurUpdate.getReference()).isEqualTo(refFournisseur);
        assertThat(fournisseurUpdate.getFirstName()).isEqualTo(firstName);
        assertThat(fournisseurUpdate.getLastName()).isEqualTo(f1.getLastName());
        assertThat(fournisseurUpdate.getEmail()).isEqualTo(f1.getEmail());

    }

    @Test
    public void testFindById() {
        Fournisseur f1 = new Fournisseur(1L, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1");

        Fournisseur fournisseurResult = fournisseurRepository.save(f1);

        boolean isExistFournisseur = fournisseurRepository.findById(fournisseurResult.getId()).isPresent();

        assertTrue(isExistFournisseur);

    }

    @Test
    public void testFindAll() {
        Fournisseur f1 = new Fournisseur(1L, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1");
        fournisseurRepository.save(f1);

        Fournisseur f2 = new Fournisseur(2L, "f2", "f2", "f2", "f2", "f2", "f2", "f2", "f2");
        fournisseurRepository.save(f2);

        List<Fournisseur> fournisseurList = fournisseurRepository.findAll();

        assertThat(fournisseurList).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Fournisseur f1 = new Fournisseur(1L, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1");
        Fournisseur fournisseurResult = fournisseurRepository.save(f1);

        boolean isExistBeforeDelete = fournisseurRepository.findById(fournisseurResult.getId()).isPresent();

        fournisseurRepository.deleteById(fournisseurResult.getId());

        boolean notExistAfterDelete = fournisseurRepository.findById(fournisseurResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
