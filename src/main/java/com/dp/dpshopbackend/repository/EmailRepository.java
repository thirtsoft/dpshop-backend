package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
