package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressLivraison;
import com.dp.dpshopbackend.models.Commande;
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
public class AddressLivraisonRepositoryTest {

    @Autowired
    private AddressLivraisonRepository addressLivraisonRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Test
    @Rollback(false)
    public void testCreateAddressLivraison() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        AddressLivraison addressLivraison = new AddressLivraison(1L, "liv", "liv", "liv", "liv", "liv", "liv", commande);

        AddressLivraison addressLivraisonResult = addressLivraisonRepository.save(addressLivraison);

        assertNotNull(addressLivraisonResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateAddressLivraison() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        AddressLivraison addressLivraison = new AddressLivraison(1L, "liv", "liv", "liv", "liv", "liv", "liv", commande);
        addressLivraisonRepository.save(addressLivraison);

        String refAddLivraison = "123EST";
        String city = "Dakar";
        addressLivraison.setId(2L);
        addressLivraison.setReference(refAddLivraison);
        addressLivraison.setCity(city);

        AddressLivraison addressLivraisonUpdate = addressLivraisonRepository.save(addressLivraison);

        assertThat(addressLivraisonUpdate.getReference()).isEqualTo(refAddLivraison);
        assertThat(addressLivraisonUpdate.getCity()).isEqualTo(city);
        assertThat(addressLivraisonUpdate.getPhone()).isEqualTo(addressLivraison.getPhone());
        assertThat(addressLivraisonUpdate.getCountry()).isEqualTo(addressLivraison.getCountry());

    }

    @Test
    public void testFindById() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        AddressLivraison addressLivraison = new AddressLivraison(1L, "liv", "liv", "liv", "liv", "liv", "liv", commande);

        AddressLivraison addressLivraisonResult = addressLivraisonRepository.save(addressLivraison);

        boolean isExistAddressLivraison = addressLivraisonRepository.findById(addressLivraisonResult.getId()).isPresent();

        assertTrue(isExistAddressLivraison);

    }

    @Test
    public void testFindAll() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        AddressLivraison addressLivraison = new AddressLivraison(1L, "liv", "liv", "liv", "liv", "liv", "liv", commande);
        addressLivraisonRepository.save(addressLivraison);

        AddressLivraison addressLivraison1 = new AddressLivraison(2L, "liv2", "liv2", "liv2", "liv2", "liv2", "liv", commande);
        addressLivraisonRepository.save(addressLivraison1);

        List<AddressLivraison> addresseLivraisons = addressLivraisonRepository.findAll();

        assertThat(addresseLivraisons).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long comId = (long) 1;
        Commande commande = commandeRepository.findById(comId).orElse(null);

        AddressLivraison addressLivraison = new AddressLivraison(1L, "liv", "liv", "liv", "liv", "liv", "liv", commande);

        AddressLivraison addressLivraisonResult = addressLivraisonRepository.save(addressLivraison);

        boolean isExistBeforeDelete = addressLivraisonRepository.findById(addressLivraisonResult.getId()).isPresent();

        addressLivraisonRepository.deleteById(addressLivraisonResult.getId());

        boolean notExistAfterDelete = addressLivraisonRepository.findById(addressLivraisonResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
