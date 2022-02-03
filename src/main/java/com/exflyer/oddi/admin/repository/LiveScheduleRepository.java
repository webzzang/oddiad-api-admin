package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.LiveSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LiveScheduleRepository extends JpaRepository<LiveSchedule, Long>, JpaSpecificationExecutor<LiveSchedule> {

}
