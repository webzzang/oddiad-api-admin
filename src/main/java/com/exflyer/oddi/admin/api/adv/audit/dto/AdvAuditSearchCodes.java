package com.exflyer.oddi.admin.api.adv.audit.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvAuditSearchCodes {

  @ApiModelProperty(value = "광고채널코드", position = 1)
  private List<Code> advChannelCode;

  @ApiModelProperty(value = "심사상태코드", position = 2)
  private List<Code> auditCode;

  @ApiModelProperty(value = "거절코드", position = 3)
  private List<Code> rejectionCode;

  @ApiModelProperty(value = "디자인코드", position = 4)
  private List<Code> designCode;

  @ApiModelProperty(value = "진행코드", position = 5)
  private List<Code> progressCode;

  public AdvAuditSearchCodes(List<Code> advChannelCode, List<Code> auditCode, List<Code> rejectionCode, List<Code> designCode, List<Code> progressCode) {
    this.advChannelCode = advChannelCode;
    this.auditCode = auditCode;
    this.rejectionCode = rejectionCode;
    this.designCode = designCode;
    this.progressCode = progressCode;
  }
}
