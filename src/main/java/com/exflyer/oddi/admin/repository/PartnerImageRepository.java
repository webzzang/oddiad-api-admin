package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartnerImageRepository extends JpaRepository<PartnerImage, Long>, JpaSpecificationExecutor<PartnerImage> {

    List<PartnerImage> findByPartnerSeq(@Param("partnerSeq") Long partnerSeq);

    @Query(value = "select file_seq from partner_image where partner_seq  = :partnerSeq and file_seq not in (:fileSeq)", nativeQuery = true)
    List<Long> isNotInFileSeq(@Param("partnerSeq") Long partnerSeq, @Param("fileSeq") List<Long> fileSeq);

}
