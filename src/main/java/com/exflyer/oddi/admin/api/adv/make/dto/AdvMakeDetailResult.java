package com.exflyer.oddi.admin.api.adv.make.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvMakeDetailResult {

  private AdvMakeBasicInfoResult advBasicInfo;
  private List<AdvMakeExpoResult> advNowExpoList;
  private List<AdvMakeExpoResult> advWaitExpoList;
  private List<AdvMakeFilesResult> advNowExpoFileList;
  private List<AdvMakeFilesResult> advWaitExpoFileList;

  public AdvMakeDetailResult(AdvMakeBasicInfoResult advBasicInfo, List<AdvMakeExpoResult> advNowExpoList, List<AdvMakeExpoResult> advWaitExpoList, List<AdvMakeFilesResult> advNowExpoFileList, List<AdvMakeFilesResult> advWaitExpoFileList){
    this.advBasicInfo = advBasicInfo;
    this.advNowExpoList = advNowExpoList;
    this.advWaitExpoList = advWaitExpoList;
    this.advNowExpoFileList = advNowExpoFileList;
    this.advWaitExpoFileList = advWaitExpoFileList;
  }

}
