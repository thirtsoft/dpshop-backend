package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Category;
import com.dp.dpshopbackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select count(p) from Client p ")
    BigDecimal countNumberOfClient();

    List<Client> findByOrderByIdDesc();

    @Query("Select DISTINCT act from  Client act where act.actif=1 ORDER BY act.id desc")
    List<Client> findAll();

}
