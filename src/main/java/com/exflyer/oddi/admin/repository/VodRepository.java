package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Vod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VodRepository extends JpaRepository<Vod, Long>, JpaSpecificationExecutor<Vod> {

}
