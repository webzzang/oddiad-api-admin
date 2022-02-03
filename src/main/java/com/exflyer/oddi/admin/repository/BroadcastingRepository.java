package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.AdvPartner;
import com.exflyer.oddi.admin.models.Broadcasting;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BroadcastingRepository extends JpaRepository<Broadcasting, Long>, JpaSpecificationExecutor<Broadcasting> {
    @Query(value="select * from broadcasting where adv_seq = :advSeq and partner_seq = :partnerSeq and device_id = :deviceId", nativeQuery = true)
    Optional<Broadcasting> findByAdvSeqByPartnerSeq(@Param("advSeq")Long advSeq, @Param("partnerSeq")Long partnerSeq, @Param("deviceId")String deviceId);
}
