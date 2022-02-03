package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.ManagerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ManagerRoleRepository extends JpaRepository<ManagerRole, Long>, JpaSpecificationExecutor<ManagerRole> {

}
