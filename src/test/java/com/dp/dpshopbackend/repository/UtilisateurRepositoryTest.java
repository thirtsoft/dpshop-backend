package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.models.Categorie;
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
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateUtilisateur() {
        String name = "thir"; String username = "Admin"; String mobile = "779440310"; String email = "thirdiallo@gmail.com";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(name); utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile); utilisateurDto.setEmail(email);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        assertNotNull(utilisateurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateUtilisateur() {
        String name = "thir"; String username = "Admin"; String mobile = "779440310"; String email = "thirdiallo@gmail.com";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(name); utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile); utilisateurDto.setEmail(email);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        String user = "Thir";
        UtilisateurDto utilisateurUpdateDto = new UtilisateurDto();
        utilisateurUpdateDto.setUsername(user);
        utilisateurUpdateDto.setId((long) 1);

        UtilisateurDto.fromEntityToDto(utilisateurRepository.save(UtilisateurDto.fromDtoToEntity(utilisateurUpdateDto)));

        assertThat(utilisateurUpdateDto.getUsername()).isEqualTo(user);

    }

    @Test
    public void testFindById() {
        String name = "thir"; String username = "Admin"; String mobile = "779440310"; String email = "thirdiallo@gmail.com";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(name); utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile); utilisateurDto.setEmail(email);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        boolean isExistUser = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        assertTrue(isExistUser);

    }

    @Test
    public void testFindAll() {
        String name = "thir"; String username = "Admin"; String mobile = "779440310"; String email = "thirdiallo@gmail.com";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(name); utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile); utilisateurDto.setEmail(email);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        String name1 = "thir"; String username1 = "Admin"; String mobile1 = "779440310"; String email1 = "thirdiallo@gmail.com";
        UtilisateurDto utilisateurDto1 = new UtilisateurDto();
        utilisateurDto1.setName(name1); utilisateurDto1.setUsername(username1);
        utilisateurDto1.setMobile(mobile1); utilisateurDto1.setEmail(email1);

        UtilisateurDto utilisateurDtoResult1 = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto1)
                )
        );

        List<?> utilisateurs = utilisateurRepository.findAll();

        assertThat(utilisateurs).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String name1 = "thir"; String username1 = "Admin"; String mobile1 = "779440310"; String email1 = "thirdiallo@gmail.com";
        UtilisateurDto utilisateurDto1 = new UtilisateurDto();
        utilisateurDto1.setName(name1); utilisateurDto1.setUsername(username1);
        utilisateurDto1.setMobile(mobile1); utilisateurDto1.setEmail(email1);

        UtilisateurDto utilisateurDtoResult1 = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto1)
                )
        );

        boolean isExistBeforeDelete = utilisateurRepository.findById(utilisateurDtoResult1.getId()).isPresent();

        utilisateurRepository.deleteById(utilisateurDtoResult1.getId());

        boolean notExistAfterDelete = utilisateurRepository.findById(utilisateurDtoResult1.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }
}
