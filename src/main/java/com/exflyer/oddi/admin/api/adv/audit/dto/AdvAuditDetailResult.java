package com.exflyer.oddi.admin.api.adv.audit.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvAuditDetailResult {

  @DecryptField
  private AdvAuditResult advBasicInfo;
  private List<AdvAuditPartnerResult> advPartnerList;
  private List<AdvAuditFileResult> advFileList;
  private List<AdvRejectListResult> rejectionList;

  public AdvAuditDetailResult(AdvAuditResult advDetail, List<AdvAuditPartnerResult> advResult, List<AdvAuditFileResult> fileResult, List<AdvRejectListResult> rejectionList){
    this.advBasicInfo = advDetail;
    this.advPartnerList = advResult;
    this.advFileList = fileResult;
    this.rejectionList = rejectionList;
  }

}
