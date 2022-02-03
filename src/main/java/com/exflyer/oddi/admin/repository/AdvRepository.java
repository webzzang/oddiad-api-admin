package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Adv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdvRepository extends JpaRepository<Adv, Long>, JpaSpecificationExecutor<Adv> {

}
