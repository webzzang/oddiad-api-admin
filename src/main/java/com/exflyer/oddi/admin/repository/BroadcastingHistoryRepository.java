package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.BroadcastingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BroadcastingHistoryRepository extends JpaRepository<BroadcastingHistory, Long>,
  JpaSpecificationExecutor<BroadcastingHistory> {

}
