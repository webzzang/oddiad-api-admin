package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerDefaultAdvFiles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartnerDefailtAdvFilesRepository extends JpaRepository<PartnerDefaultAdvFiles, String>, JpaSpecificationExecutor<PartnerDefaultAdvFiles> {

    @Query(value = "select default_adv_file_seq from partner_default_adv_files where default_adv_file_seq not in (:fileSeq)", nativeQuery = true)
    List<Long> isNotInFileSeq(@Param("fileSeq") List<Long> fileSeq);
}
