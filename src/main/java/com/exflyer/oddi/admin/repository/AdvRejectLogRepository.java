package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.AdvRejectLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvRejectLogRepository extends JpaRepository<AdvRejectLog, Long>, JpaSpecificationExecutor<AdvRejectLog> {

}
