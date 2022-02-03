package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.AdvFile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdvFileRepository extends JpaRepository<AdvFile, String>, JpaSpecificationExecutor<AdvFile> {

    List<AdvFile> findByAdvSeq(@Param("advSeq") Long advSeq);

    @Query(value = "select file_seq from adv_file where adv_seq = :advSeq", nativeQuery = true)
    List<Long> findAllByFileSeq(@Param("advSeq") Long advSeq);

    @Query(value = "select file_seq from adv_file where adv_seq  = :advSeq and file_seq not in (:fileSeq)", nativeQuery = true)
    List<Long> isNotInFileSeq(@Param("advSeq") Long advSeq, @Param("fileSeq") List<Long> fileSeq);

    @Modifying
    @Transactional
    @Query(value = "delete from adv_file where adv_seq = :advSeq", nativeQuery = true)
    void deleteAdvFile(@Param("advSeq") Long advSeq);

}
