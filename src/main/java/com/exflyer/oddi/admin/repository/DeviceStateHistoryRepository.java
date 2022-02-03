package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.DeviceStateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceStateHistoryRepository extends JpaRepository<DeviceStateHistory, Long>,
  JpaSpecificationExecutor<DeviceStateHistory> {

}
