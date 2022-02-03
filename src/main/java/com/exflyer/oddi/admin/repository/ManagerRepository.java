package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ManagerRepository extends JpaRepository<Manager, String>, JpaSpecificationExecutor<Manager> {


}
