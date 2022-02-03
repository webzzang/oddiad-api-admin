package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.YoutubePartner;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface YouTubePartnerRepository extends JpaRepository<YoutubePartner, String>, JpaSpecificationExecutor<YoutubePartner> {

    @Transactional
    @Modifying
    @Query(value = "delete from youtube_partner where youtube_id = :youtubeId", nativeQuery = true)
    void updateByPartnerByVod(@Param("youtubeId") String youtubeId);

    @Transactional
    @Modifying
    @Query(value = "update youtube_partner set partner_seq = :partnerSeq, expo = :expo where youtube_id = :youtubeId", nativeQuery = true)
    void insertByYoutube(@Param("partnerSeq") Long partnerSeq, @Param("expo") Boolean expo, @Param("youtubeId") String youtubeId);
}
