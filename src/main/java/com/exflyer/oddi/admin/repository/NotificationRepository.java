package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationRepository extends JpaRepository<Notification, Long>,
  JpaSpecificationExecutor<Notification> {

}
