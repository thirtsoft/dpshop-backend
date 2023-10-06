package com.dp.dpshopbackend.repository;

import com.dp.dpshopbackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.actif=1 order by n.createdDate desc")
    List<Notification> findTop3ByOrderByCreatedDateDesc();

    @Query("select count(c) from Notification c where c.actif=1 and month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfNotification();

    @Query("select count(c) from Notification c where c.actif=1 and  c.article.reference =:prod")
    BigDecimal countNumberOfNotificationByProductId(@Param("prod") String prodRef);

    @Query("select n from Notification n where n.actif=1 and n.article.reference =:num")
    List<Notification> findTop4ByOrderByCreatedDateDesc(@Param("num") String prodRef);

    @Query("Select DISTINCT act from Notification act where act.actif=1 ORDER BY act.id desc")
    List<Notification> findAll();

}
