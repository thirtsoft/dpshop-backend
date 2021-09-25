package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findTop3ByOrderByCreatedDateDesc();

}
