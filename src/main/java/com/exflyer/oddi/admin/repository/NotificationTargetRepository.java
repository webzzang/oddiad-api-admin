package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.NotificationTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationTargetRepository extends JpaRepository<NotificationTarget, Long>,
  JpaSpecificationExecutor<NotificationTarget> {

}
