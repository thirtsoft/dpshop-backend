package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Notification;
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
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateNotification() {
        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        //   Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article, utilisateur);
        Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article);

        Notification notificationResult = notificationRepository.save(notification);

        assertNotNull(notificationResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateNotification() {
        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        //    Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article, utilisateur);
        Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article);
        notificationRepository.save(notification);

        String reference = "Not";
        String nbreEtoile = "5etoiles";
        notification.setId(2L);
        notification.setReference(reference);
        notification.setNbreEtoile(nbreEtoile);

        Notification notificationUpdate = notificationRepository.save(notification);

        assertThat(notificationUpdate.getReference()).isEqualTo(reference);
        assertThat(notificationUpdate.getNbreEtoile()).isEqualTo(nbreEtoile);

    }

    @Test
    public void testFindById() {
        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        //    Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article, utilisateur);
        Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article);

        Notification notificationResult = notificationRepository.save(notification);

        boolean isExistNotification = notificationRepository.findById(notificationResult.getId()).isPresent();

        assertTrue(isExistNotification);

    }

    @Test
    public void testFindAll() {
        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        //   Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article, utilisateur);
        Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article);
        notificationRepository.save(notification);

        //    Notification notification1 = new Notification(2L, "Not2", "bien22", "4etoiles22", article, utilisateur);
        Notification notification1 = new Notification(2L, "Not2", "bien22", "4etoiles22", article);
        notificationRepository.save(notification1);

        List<Notification> notifications = notificationRepository.findAll();

        assertThat(notifications).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long artId = (long) 1;
        Article article = articleRepository.findById(artId).orElse(null);

        Long userId = (long) 1;
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElse(null);

        //    Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article, utilisateur);
        Notification notification = new Notification(1L, "Not1", "bien", "4etoiles", article);
        Notification notificationResult = notificationRepository.save(notification);

        boolean isExistBeforeDelete = notificationRepository.findById(notificationResult.getId()).isPresent();

        notificationRepository.deleteById(notificationResult.getId());

        boolean notExistAfterDelete = notificationRepository.findById(notificationResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }


}
