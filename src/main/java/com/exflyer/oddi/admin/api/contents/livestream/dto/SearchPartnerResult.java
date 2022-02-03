package com.exflyer.oddi.admin.api.contents.livestream.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchPartnerResult {

  @ApiModelProperty(value = "파트너 순번", position = 1)
  private Long partnerSeq;

  @ApiModelProperty(value = "채널타입코드", position = 2)
  private String channelType;

  @ApiModelProperty(value = "채널타입명", position = 3)
  private String channelTypeName;

  @ApiModelProperty(value = "광고처이름", position = 4)
  private String mallName;

  @ApiModelProperty(value = "가격", position = 5)
  private String slotPrice;

  @ApiModelProperty(value = "등록일시", position = 6)
  private String regDate;

  @ApiModelProperty(value = "운영여부", position = 7)
  private String operation;

  @ApiModelProperty(value = "운영여부명", position = 8)
  private String operationName;


}
