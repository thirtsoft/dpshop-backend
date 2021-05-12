package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.dto.*;
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
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    @Rollback(false)
    public void testCreateNotification() {
        String firstName = "tairou"; String lastName = "Diallo";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);

        String reference = "Art1"; String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference); articleDto.setDesignation(designationArticle);

         String referenceNotification = "Not1"; String nbreEtoile = "4etoiles"; String observation = "Bien";
         NotificationDto notificationDto = new NotificationDto();
         notificationDto.setReference(referenceNotification); notificationDto.setNbreEtoile(nbreEtoile);
         notificationDto.setObservation(observation); notificationDto.setArticleDto(articleDto);
         notificationDto.setUtilisateurDto(utilisateurDto);

        NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );

        assertNotNull(notificationDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateNotification() {
        String firstName = "tairou"; String lastName = "Diallo";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);

        String reference = "Art1"; String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference); articleDto.setDesignation(designationArticle);

        String referenceNotification = "Not1"; String nbreEtoile = "4etoiles"; String observation = "Bien";
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReference(referenceNotification); notificationDto.setNbreEtoile(nbreEtoile);
        notificationDto.setObservation(observation); notificationDto.setArticleDto(articleDto);
        notificationDto.setUtilisateurDto(utilisateurDto);

        NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );

        String refNote = "Note120";
        NotificationDto notificationUpdateDto = new NotificationDto();
        notificationUpdateDto.setReference(refNote);
        notificationUpdateDto.setId(1);

        NotificationDto.fromEntityToDto(notificationRepository.save(NotificationDto.fromDtoToEntity(notificationUpdateDto)));

        assertThat(notificationUpdateDto.getReference()).isEqualTo(refNote);

    }

    @Test
    public void testFindById() {
        String firstName = "tairou"; String lastName = "Diallo";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);

        String reference = "Art1"; String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference); articleDto.setDesignation(designationArticle);

        String referenceNotification = "Not1"; String nbreEtoile = "4etoiles"; String observation = "Bien";
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReference(referenceNotification); notificationDto.setNbreEtoile(nbreEtoile);
        notificationDto.setObservation(observation); notificationDto.setArticleDto(articleDto);
        notificationDto.setUtilisateurDto(utilisateurDto);

        NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
        boolean isExistNotification = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        assertTrue(isExistNotification);

    }

    @Test
    public void testFindAll() {
        String firstName = "tairou"; String lastName = "Diallo";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);

        String reference = "Art1"; String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference); articleDto.setDesignation(designationArticle);

        String referenceNotification = "Not1"; String nbreEtoile = "4etoiles"; String observation = "Bien";
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReference(referenceNotification); notificationDto.setNbreEtoile(nbreEtoile);
        notificationDto.setObservation(observation); notificationDto.setArticleDto(articleDto);
        notificationDto.setUtilisateurDto(utilisateurDto);

        NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );

        String refNotif= "Address-126";
        NotificationDto notificationDto1 = new NotificationDto();
        notificationDto1.setReference(refNotif);

        NotificationDto notificationDtoResult1 = NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto1)
                )
        );

        List<?> notifications = notificationRepository.findAll();

        assertThat(notifications).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String firstName = "tairou"; String lastName = "Diallo";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName(firstName); utilisateurDto.setUsername(lastName);

        String reference = "Art1"; String designationArticle = "HP-ProBook";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference); articleDto.setDesignation(designationArticle);

        String referenceNotification = "Not1"; String nbreEtoile = "4etoiles"; String observation = "Bien";
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setReference(referenceNotification); notificationDto.setNbreEtoile(nbreEtoile);
        notificationDto.setObservation(observation); notificationDto.setArticleDto(articleDto);
        notificationDto.setUtilisateurDto(utilisateurDto);

        NotificationDto notificationDtoResult = NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );

        boolean isExistBeforeDelete = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        notificationRepository.deleteById(notificationDtoResult.getId());

        boolean notExistAfterDelete = notificationRepository.findById(notificationDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
