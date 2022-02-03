package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerDevicePushHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartnerDevicePushHistoryRepository extends JpaRepository<PartnerDevicePushHistory, String>, JpaSpecificationExecutor<PartnerDevicePushHistory> {

}
