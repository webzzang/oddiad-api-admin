package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.DeviceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceStateRepository extends JpaRepository<DeviceState, String>,
  JpaSpecificationExecutor<DeviceState> {

}
