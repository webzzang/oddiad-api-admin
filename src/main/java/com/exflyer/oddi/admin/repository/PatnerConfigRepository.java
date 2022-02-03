package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatnerConfigRepository extends JpaRepository<PartnerConfig, Integer>,
  JpaSpecificationExecutor<PartnerConfig> {

}
