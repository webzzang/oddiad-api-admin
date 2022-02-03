package com.exflyer.oddi.admin.api.adv.audit.dao;

import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditFileResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditListSearchReq;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditListSearchResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditPartnerResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditResult;
import com.exflyer.oddi.admin.api.adv.audit.dto.AdvRejectListResult;
import com.github.pagehelper.Page;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvAuditMapper {

  Page<AdvAuditListSearchResult> findBySearchReq(AdvAuditListSearchReq searchReq);
  AdvAuditResult findDetailAdvBasicInfo(Long seq);
  List<AdvAuditPartnerResult> findDetailAdvPartnerList(Long seq, String oddiZoneType);
  List<AdvAuditFileResult> findDetailAdvFileList(Long seq);
  List<AdvRejectListResult> findDetailRejectionList(Long seq);
}
