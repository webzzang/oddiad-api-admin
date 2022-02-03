package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.BroadcastingFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BroadcastingFileRepository extends JpaRepository<BroadcastingFile, Long>, JpaSpecificationExecutor<BroadcastingFile> {

    @Query(value = "delete from broadcasting_file where broadcasting_seq = :broadcastingSeq", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteByBroadcastingSeq(@Param("broadcastingSeq")Long broadcastingSeq);

}
