package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long>,
  JpaSpecificationExecutor<NotificationHistory> {

}
