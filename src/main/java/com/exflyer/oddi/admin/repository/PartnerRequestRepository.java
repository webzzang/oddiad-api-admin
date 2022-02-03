package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartnerRequestRepository extends JpaRepository<PartnerRequest, Long>,
  JpaSpecificationExecutor<PartnerRequest> {

}
