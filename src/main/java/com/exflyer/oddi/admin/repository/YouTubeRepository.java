package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.Youtube;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface YouTubeRepository extends JpaRepository<Youtube, Long>, JpaSpecificationExecutor<Youtube> {

    @Query(value = "select count(1) from youtube", nativeQuery = true)
    int findByYouTubeCnt();

    @Query(value = "select * from youtube where partner_seq = :partnerSeq and youtube_id = :youtubeId", nativeQuery = true)
    Optional<Youtube> findByYoutubeIdByParentSeq(@Param("partnerSeq") Long partnerSeq, @Param("youtubeId") String youtubeId);

    @Query(value = "select * from youtube where youtube_id = :youtubeId", nativeQuery = true)
    Optional<Youtube> findByYoutubeIdByParentSeq(@Param("youtubeId") String youtubeId);

    @Transactional
    @Modifying
    @Query(value = "update youtube set partner_seq = null where youtube_id = :youtubeId", nativeQuery = true)
    void updateByPartnerByVod(@Param("youtubeId") String youtubeId);

    @Transactional
    @Modifying
    @Query(value = "update youtube set partner_seq = :partnerSeq, expo = :expo where youtube_id = :youtubeId", nativeQuery = true)
    void updateByYoutube(@Param("partnerSeq") Long partnerSeq, @Param("expo") Boolean expo, @Param("youtubeId") String youtubeId);

    @Transactional
    @Modifying
    @Query(value = "update youtube set partner_seq = null", nativeQuery = true)
    void saveAllNullPartnerSeq();

    @Transactional
    @Modifying
    @Query(value = "update youtube set expo = :expo where youtube_id = :youtubeId", nativeQuery = true)
    void updateByYoutubeExpo(@Param("expo") Boolean expo, @Param("youtubeId") String youtubeId);
}
