package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.State;
import com.dp.dpshopbackend.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByUsername(String username);

    Optional<Utilisateur> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<Utilisateur> findByOrderByIdDesc();

    @Query("Select DISTINCT act from State act where act.actif=1 ORDER BY act.name")
    List<Utilisateur> findAll();

}
