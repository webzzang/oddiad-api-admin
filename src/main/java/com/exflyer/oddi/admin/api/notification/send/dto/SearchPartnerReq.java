package com.exflyer.oddi.admin.api.notification.send.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class SearchPartnerReq extends PagingSearch {

  @ApiModelProperty(value = "채널타입코드", position = 1, required = true)
  private String channelType;

  @ApiModelProperty(value = "광고처이름", position = 2)
  private String mallName;

  @ApiModelProperty(value = "제외시킬 파트너 순번", position = 3)
  private List<Long> excludeSeq;

  @ApiModelProperty(value = "운영여부 그룹코드", hidden = true)
  private String operationGroupCode;

  @ApiModelProperty(value = "오늘날짜(YYYYMMDD)", hidden = true)
  private String nowDate;

  @ApiModelProperty(value = "승인 심사코드", hidden = true)
  private String auditApprovalCode;

  @ApiModelProperty(value = "결제완료 진행코드", hidden = true)
  private String progressPaymentCompleteCode;

}
