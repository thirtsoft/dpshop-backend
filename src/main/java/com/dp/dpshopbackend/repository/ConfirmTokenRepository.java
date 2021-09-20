package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.ConfirmToken;
import com.dp.dpshopbackend.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmTokenRepository extends JpaRepository<ConfirmToken, Long> {

    ConfirmToken findTokenByUtilisateur(Utilisateur utilisateur);

    ConfirmToken findTokenByToken(String token);

}
