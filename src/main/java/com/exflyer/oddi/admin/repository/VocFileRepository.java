package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.VocFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VocFileRepository extends JpaRepository<VocFile, Long>, JpaSpecificationExecutor<VocFile> {

}
