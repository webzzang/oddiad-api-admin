package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.DeviceInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String>, JpaSpecificationExecutor<DeviceInfo> {

    @Query(value = "select count(1) from device_info where device_id in (:deviceId)", nativeQuery = true)
    int countDeviceInfo(@Param("deviceId") List<String> deviceId);

    @Query(value = "select * from device_info where device_id = :deviceId", nativeQuery = true)
    DeviceInfo findAllByPartnerDevice(@Param("deviceId") String deviceId);

}
