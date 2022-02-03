package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Voc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VocRepository extends JpaRepository<Voc, Long>, JpaSpecificationExecutor<Voc> {

}
