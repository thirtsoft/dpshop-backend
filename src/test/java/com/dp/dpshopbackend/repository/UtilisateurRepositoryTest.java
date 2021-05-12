package com.dp.dpshopbackend.repository;

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
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateUtilisateur() {
        String name = "thir";
        String username = "Admin";
        String mobile = "779440310";
        String email = "thirdiallo@gmail.com";

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName(name);
        utilisateur.setUsername(username);
        utilisateur.setMobile(mobile);
        utilisateur.setEmail(email);

        Utilisateur utilisateurResult = utilisateurRepository.save(utilisateur);

        assertNotNull(utilisateurResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateUtilisateur() {
        String name = "thir";
        String username = "Admin";
        String mobile = "779440310";
        String email = "thirdiallo@gmail.com";

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName(name);
        utilisateur.setUsername(username);
        utilisateur.setMobile(mobile);
        utilisateur.setEmail(email);
        utilisateurRepository.save(utilisateur);

        String nameUser = "tairou";
        String usernameUser = "thirdiallo";
        utilisateur.setId(2L);
        utilisateur.setName(nameUser);
        utilisateur.setUsername(usernameUser);

        Utilisateur utilisateurUpdate = utilisateurRepository.save(utilisateur);

        assertThat(utilisateurUpdate.getName()).isEqualTo(nameUser);
        assertThat(utilisateurUpdate.getUsername()).isEqualTo(usernameUser);
        assertThat(utilisateurUpdate.getMobile()).isEqualTo(utilisateur.getMobile());
        assertThat(utilisateurUpdate.getEmail()).isEqualTo(utilisateur.getEmail());

    }

    @Test
    public void testFindById() {
        String name = "thir";
        String username = "Admin";
        String mobile = "779440310";
        String email = "thirdiallo@gmail.com";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName(name);
        utilisateur.setUsername(username);
        utilisateur.setMobile(mobile);
        utilisateur.setEmail(email);
        Utilisateur utilisateurResult = utilisateurRepository.save(utilisateur);

        boolean isExistUser = utilisateurRepository.findById(utilisateurResult.getId()).isPresent();

        assertTrue(isExistUser);

    }

    @Test
    public void testFindAll() {
        String name = "thir";
        String username = "Admin";
        String mobile = "779440310";
        String email = "thirdiallo@gmail.com";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName(name);
        utilisateur.setUsername(username);
        utilisateur.setMobile(mobile);
        utilisateur.setEmail(email);
        utilisateurRepository.save(utilisateur);

        String nameUser = "thirdiallo";
        String usernameUser = "Admin";
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(1L);
        utilisateur1.setName(nameUser);
        utilisateur1.setUsername(usernameUser);
        utilisateurRepository.save(utilisateur1);

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();

        assertThat(utilisateurList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String name = "thir";
        String username = "Admin";
        String mobile = "779440310";
        String email = "thirdiallo@gmail.com";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setName(name);
        utilisateur.setUsername(username);
        utilisateur.setMobile(mobile);
        utilisateur.setEmail(email);
        Utilisateur utilisateurResult = utilisateurRepository.save(utilisateur);

        boolean isExistBeforeDelete = utilisateurRepository.findById(utilisateurResult.getId()).isPresent();

        utilisateurRepository.deleteById(utilisateurResult.getId());

        boolean notExistAfterDelete = utilisateurRepository.findById(utilisateurResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }
}
