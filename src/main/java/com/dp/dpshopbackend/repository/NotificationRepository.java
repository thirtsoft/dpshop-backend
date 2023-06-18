package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Newsletter;
import com.dp.dpshopbackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findTop3ByOrderByCreatedDateDesc();

    List<Notification> findByOrderByIdDesc();

    @Query("select count(c) from Notification c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfNotification();

    @Query("select count(c) from Notification c where c.article.reference =:prod")
    BigDecimal countNumberOfNotificationByProductId(@Param("prod") String prodRef);

    @Query("select n from Notification n where n.article.reference =:num")
    List<Notification> findTop4ByOrderByCreatedDateDesc(@Param("num") String prodRef);

    @Query("Select DISTINCT act from Notification act where act.actif=1 ORDER BY act.id desc")
    List<Notification> findAll();

}
