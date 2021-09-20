package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.Utilisateur;
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
public class CommandeRepositoryTest {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateCommande() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        Long userId = 1L;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        String reference = "Com120";
        Long numeroCommande = 120L;
        double total = 30000;
        Commande commande = new Commande();
        commande.setId(1L);
        commande.setReference(reference);
        commande.setNumeroCommande(numeroCommande);
        commande.setTotalCommande(total);
        //      commande.setClient(client);
        commande.setUtilisateur(utilisateur);

        Commande commandeResult = commandeRepository.save(commande);

        assertNotNull(commandeResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCommande() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        Long userId = 1L;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        String reference = "Com120";
        Long numeroCommande = 120L;
        double total = 30000;
        Commande commande = new Commande();
        commande.setId(1L);
        commande.setReference(reference);
        commande.setNumeroCommande(numeroCommande);
        commande.setTotalCommande(total);
        //      commande.setClient(client);
        commande.setUtilisateur(utilisateur);

        Commande commandeResult = commandeRepository.save(commande);

        String refCom = "RefCom";
        Long numCom = 120L;
        commande.setId(2L);
        commande.setReference(refCom);
        commande.setNumeroCommande(numCom);

        Commande commandeUpdate = commandeRepository.save(commande);

        assertThat(commandeUpdate.getReference()).isEqualTo(refCom);
        assertThat(commandeUpdate.getNumeroCommande()).isEqualTo(numCom);
        assertThat(commandeUpdate.getTotalCommande()).isEqualTo(commande.getTotalCommande());

    }

    @Test
    public void testFindById() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        Long userId = 1L;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        String reference = "Com120";
        Long numeroCommande = 120L;
        double total = 30000;
        Commande commande = new Commande();
        commande.setId(1L);
        commande.setReference(reference);
        commande.setNumeroCommande(numeroCommande);
        commande.setTotalCommande(total);
        //       commande.setClient(client);
        commande.setUtilisateur(utilisateur);

        Commande commandeResult = commandeRepository.save(commande);

        boolean isExistCommande = commandeRepository.findById(commandeResult.getId()).isPresent();

        assertTrue(isExistCommande);

    }

    @Test
    public void testFindAll() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        Long userId = 1L;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        String reference = "Com120";
        Long numeroCommande = 120L;
        double total = 30000;
        Commande commande = new Commande();
        commande.setId(1L);
        commande.setReference(reference);
        commande.setNumeroCommande(numeroCommande);
        commande.setTotalCommande(total);
        //      commande.setClient(client);
        commande.setUtilisateur(utilisateur);

        commandeRepository.save(commande);

        String refCom = "refCom120";
        Commande commande1 = new Commande();
        commande.setId(2L);
        commande.setReference(refCom);
        commandeRepository.save(commande1);

        List<Commande> commandes = commandeRepository.findAll();

        assertThat(commandes).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long clientId = (long) 1;
        Client client = clientRepository.findById(clientId).orElse(null);

        Long userId = 1L;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        String reference = "Com120";
        Long numeroCommande = 120L;
        double total = 30000;
        Commande commande = new Commande();
        commande.setId(1L);
        commande.setReference(reference);
        commande.setNumeroCommande(numeroCommande);
        commande.setTotalCommande(total);
        //     commande.setClient(client);
        commande.setUtilisateur(utilisateur);
        Commande commandeResult = commandeRepository.save(commande);

        boolean isExistBeforeDelete = commandeRepository.findById(commandeResult.getId()).isPresent();

        commandeRepository.deleteById(commandeResult.getId());

        boolean notExistAfterDelete = commandeRepository.findById(commandeResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}
