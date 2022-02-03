package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.NoticeFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeFilesRepository extends JpaRepository<NoticeFiles, Long>, JpaSpecificationExecutor<NoticeFiles> {

    NoticeFiles findAllByNoticeSeqOrderByNoticeSeq(@Param("noticeSeq") Long noticeSeq);

    @Query(value = "select count(1) from notice_files where notice_seq = :noticeSeq and file_seq = :fileSeq", nativeQuery = true)
    int isFileDuplicate(@Param("noticeSeq") Long noticeSeq, @Param("fileSeq") Long fileSeq);

}
