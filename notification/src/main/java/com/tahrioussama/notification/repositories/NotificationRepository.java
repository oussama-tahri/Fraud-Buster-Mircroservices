package com.tahrioussama.notification.repositories;

import com.tahrioussama.notification.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
