package com.exflyer.oddi.admin.repository;

import com.exflyer.oddi.admin.models.MemberPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberPromotionRepository extends JpaRepository<MemberPromotion, Long>,
  JpaSpecificationExecutor<MemberPromotion> {

}
