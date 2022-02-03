package com.exflyer.oddi.admin.api.adv.make.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdvMakeDetailSearchReq extends PagingSearch {

  @ApiModelProperty(value = "파트너 순번", hidden = true)
  private Long seq;

  @ApiModelProperty(value = "장비 ID", hidden = true)
  private String deviceId;

  @ApiModelProperty(value = "오늘날짜(YYYYMMDD)", hidden = true)
  private String nowDate;

  @ApiModelProperty(value = "승인 심사코드", hidden = true)
  private String auditApprovalCode;

  @ApiModelProperty(value = "결제완료 진행코드", hidden = true)
  private String progressPaymentCompleteCode;

  @ApiModelProperty(value = "오디존 채널타입코드", hidden = true)
  private String oddiChannelType;


}
