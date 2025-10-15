package com.atlasculinary.repositories;

import com.atlasculinary.entities.Notification;
import com.atlasculinary.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    List<Notification> findTop10ByAccount_AccountIdAndIsReadFalseOrderByCreatedAtDesc(UUID accountId);

    @Modifying
    @Query("UPDATE Notification n SET n.isRead = TRUE WHERE n.account.accountId = :accountId")
    void markAllAsReadByAccountId(@Param("accountId") UUID accountId);

    Page<Notification> findByAccount_AccountId(UUID accountId, Pageable pageable);


    long countByAccount_AccountIdAndIsReadFalse(UUID accountId);
}
