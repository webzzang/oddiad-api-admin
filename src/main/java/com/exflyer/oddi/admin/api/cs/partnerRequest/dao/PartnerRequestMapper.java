package com.exflyer.oddi.admin.api.cs.partnerRequest.dao;

import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestListSearchReq;
import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestListSearchResult;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRequestMapper {
  Page<PartnerRequestListSearchResult> findBySearchReq(PartnerRequestListSearchReq searchReq);
}
