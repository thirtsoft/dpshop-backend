package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findTop3ByOrderByCreatedDateDesc();

    @Query("select count(c) from Notification c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfNotification();

}
