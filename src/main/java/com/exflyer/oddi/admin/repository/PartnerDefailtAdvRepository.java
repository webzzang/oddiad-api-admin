package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerDefaultAdv;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartnerDefailtAdvRepository extends JpaRepository<PartnerDefaultAdv, String>, JpaSpecificationExecutor<PartnerDefaultAdv> {

    @Query(value = "select * from partner_default_adv where partner_seq = :partnerSeq", nativeQuery = true)
    Optional<PartnerDefaultAdv>findByChannelTypeByPartnerSeq(@Param("partnerSeq")Long partnerSeq);
}
