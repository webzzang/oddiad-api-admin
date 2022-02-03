package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerSubway;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PartnerSubwayRepository extends JpaRepository<PartnerSubway, Long>, JpaSpecificationExecutor<PartnerSubway> {

    @Query(value = "select subway_seq, partner_seq, reg_date, reg_id, subway_code from partner_subway where partner_seq = :partnerSeq", nativeQuery = true)
    List<PartnerSubway> findByPartnerSeq(@Param("partnerSeq") Long partnerSeq);

    @Modifying
    @Transactional
    @Query(value = "delete from partner_subway where partner_seq = :partnerSeq", nativeQuery = true)
    void deleteSubway(@Param("partnerSeq") Long partnerSeq);

}
