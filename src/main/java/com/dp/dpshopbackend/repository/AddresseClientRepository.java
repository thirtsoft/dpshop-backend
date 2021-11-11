package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressClient;
import com.dp.dpshopbackend.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddresseClientRepository extends JpaRepository<AddressClient, Long> {

    List<AddressClient> findByOrderByIdDesc();
}
