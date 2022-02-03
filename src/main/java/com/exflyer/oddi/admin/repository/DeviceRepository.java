package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceRepository extends JpaRepository<Device, String>, JpaSpecificationExecutor<Device> {

}
