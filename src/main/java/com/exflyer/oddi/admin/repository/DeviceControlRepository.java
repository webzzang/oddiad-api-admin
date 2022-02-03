package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.DeviceControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceControlRepository extends JpaRepository<DeviceControl, Long>,
  JpaSpecificationExecutor<DeviceControl> {

}
