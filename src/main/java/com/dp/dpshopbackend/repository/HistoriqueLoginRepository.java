package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Fournisseur;
import com.dp.dpshopbackend.models.HistoriqueLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HistoriqueLoginRepository extends JpaRepository<HistoriqueLogin, Long> {

    @Query("select count(f) from HistoriqueLogin f ")
    BigDecimal countNumberOfHistoriqueLogins();

    List<HistoriqueLogin> findByOrderByIdDesc();

    @Query("Select DISTINCT act from HistoriqueLogin act where act.actif=1 ORDER BY act.id desc")
    List<HistoriqueLogin> findAll();

}
