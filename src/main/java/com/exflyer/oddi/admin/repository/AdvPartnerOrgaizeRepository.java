package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.AdvPartnerOrganize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvPartnerOrgaizeRepository extends JpaRepository<AdvPartnerOrganize, Long>, JpaSpecificationExecutor<AdvPartnerOrganize> {

}
