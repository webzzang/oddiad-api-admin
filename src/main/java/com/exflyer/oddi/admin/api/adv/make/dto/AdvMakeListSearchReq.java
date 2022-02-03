package com.exflyer.oddi.admin.api.adv.make.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdvMakeListSearchReq extends PagingSearch {

  @ApiModelProperty(value = "광고채널", position = 1)
  private String channelType;

  @ApiModelProperty(value = "광고처 이름", position = 2)
  private String mallName;

  @ApiModelProperty(value = "기기 이름", position = 3)
  private String deviceName;

  @ApiModelProperty(value = "승인 심사코드", hidden = true)
  private String auditApprovalCode;

  @ApiModelProperty(value = "결제완료 진행코드", hidden = true)
  private String progressPaymentCompleteCode;

  /*@ApiModelProperty(value = "오늘날짜(YYYYMMDD)", hidden = true)
  private String nowDate;*/


}
