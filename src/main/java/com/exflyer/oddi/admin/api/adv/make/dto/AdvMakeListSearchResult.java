package com.exflyer.oddi.admin.api.adv.make.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvMakeListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "기기 ID", position = 2)
  private String deviceId;

  @ApiModelProperty(value = "광고처 이름", position = 3)
  private String mallName;

  @ApiModelProperty(value = "채널 타입코드", position = 4)
  private String channelType;

  @ApiModelProperty(value = "채널 타입명", position = 5)
  private String channelTypeName;

  @ApiModelProperty(value = "기기 이름", position = 6)
  private String deviceName;

  @ApiModelProperty(value = "운영광고수", position = 7)
  private Integer advCount;

  @ApiModelProperty(value = "화면분할코드", position = 8)
  private String displayDiv;

  @ApiModelProperty(value = "화면분할코드명", position = 9)
  private String displayDivName;

  @ApiModelProperty(value = "등록일", position = 10)
  private String regDate;


}
