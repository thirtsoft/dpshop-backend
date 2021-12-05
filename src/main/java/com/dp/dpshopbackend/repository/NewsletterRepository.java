package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

    @Query("select count(p) from Newsletter p ")
    BigDecimal countNumberOfNewsletters();

    List<Newsletter> findByOrderByIdDesc();

}
