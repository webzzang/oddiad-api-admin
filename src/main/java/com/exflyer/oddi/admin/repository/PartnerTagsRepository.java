package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.PartnerTags;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartnerTagsRepository extends JpaRepository<PartnerTags, Long>, JpaSpecificationExecutor<PartnerTags> {

    List<PartnerTags> findByPartnerSeq(@Param("partnerSeq") Long partnerSeq);

}
