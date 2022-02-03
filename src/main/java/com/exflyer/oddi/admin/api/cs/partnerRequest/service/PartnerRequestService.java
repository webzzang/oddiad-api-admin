package com.exflyer.oddi.admin.api.cs.partnerRequest.service;

import com.exflyer.oddi.admin.api.cs.partnerRequest.dao.PartnerRequestMapper;
import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestListSearchReq;
import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestListSearchResult;
import com.exflyer.oddi.admin.api.cs.partnerRequest.dto.PartnerRequestSearchCodes;
import com.exflyer.oddi.admin.models.Code;
import com.exflyer.oddi.admin.repository.CodeRepository;
import com.exflyer.oddi.admin.share.dto.PagingResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartnerRequestService {

  @Autowired
  private CodeRepository codeRepository;

  @Autowired
  private PartnerRequestMapper partnerRequestMapper;

  public PartnerRequestSearchCodes findCodeForSearching() {
    List<Code> businessCode = codeRepository.findByGroupCodeAndUsable("BST", true);
    return new PartnerRequestSearchCodes(businessCode);
  }

  public PagingResult<PartnerRequestListSearchResult> findBySearchReq(PartnerRequestListSearchReq searchReq) {
    PageHelper.startPage(searchReq.getPageNo(), searchReq.getPageSize());
    searchReq.setOrderBy(StringUtils.defaultIfBlank(searchReq.getOrderBy(), "seq desc"));
    PageHelper.orderBy(searchReq.getOrderBy());
    Page<PartnerRequestListSearchResult> result = partnerRequestMapper.findBySearchReq(searchReq);
    return PagingResult.createResultDto(result);
  }

}
