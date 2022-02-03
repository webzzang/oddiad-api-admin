package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import java.util.List;
import lombok.Data;

@Data
public class OddiZoneConfigDetailResult {

  private PartnerConfigDetailResult partnerConfigDetailResult;
  private List<PartnerDefaultAdvFileResult> partnerDefaultAdvFileList;

  public OddiZoneConfigDetailResult(PartnerConfigDetailResult partnerConfigDetailResult, List<PartnerDefaultAdvFileResult> partnerDefaultAdvFileList){
    this.partnerConfigDetailResult = partnerConfigDetailResult;
    this.partnerDefaultAdvFileList = partnerDefaultAdvFileList;
  }

}
