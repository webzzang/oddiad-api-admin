package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.ManagerNotification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ManagerNotificationRepository extends JpaRepository<ManagerNotification, Long>, JpaSpecificationExecutor<ManagerNotification> {

    @Transactional
    @Modifying
    @Query(value = "delete from manager_notification where role_seq = :roleSeq", nativeQuery = true)
    void deleteManagerNotification(@Param("roleSeq") Long roleSeq);

    @Transactional
    @Modifying
    @Query(value = "update manager_notification set device_push = :devicePush where role_seq = :roleSeq and device_state_code = :deviceStateCode", nativeQuery = true)
    void updateManagerNotification(@Param("devicePush") Boolean devicePush, @Param("roleSeq") Long roleSeq, @Param("deviceStateCode") String deviceStateCode);

}
