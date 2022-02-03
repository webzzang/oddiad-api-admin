package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.AdvPartner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdvPartnerRepository extends JpaRepository<AdvPartner, Long>, JpaSpecificationExecutor<AdvPartner> {

    @Query(value="select * from adv_partner where adv_seq = :advSeq and partner_seq = :partnerSeq", nativeQuery = true)
    Optional<AdvPartner> findByAdvSeqByPartnerSeq(@Param("advSeq")Long advSeq, @Param("partnerSeq")Long partnerSeq);

}
