package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.AddressClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresseClientRepository extends JpaRepository<AddressClient, Long> {
}
