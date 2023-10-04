package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select count(p) from Client p where p.actif=1")
    BigDecimal countNumberOfClient();

    @Query("Select DISTINCT act from  Client act where act.actif=1 ORDER BY act.firstName asc")
    List<Client> findAll();

}
