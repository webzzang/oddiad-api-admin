package com.exflyer.oddi.admin.api.partner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OddiZonePartnerDeviceResult {

  @ApiModelProperty(value = "디바이스 ID(매체 ID)", position = 1)
  private String deviceId;

  @ApiModelProperty(value = "파트너 순번", position = 2)
  private Long partnerSeq;

  @ApiModelProperty(value = "기기명", position = 3)
  private String name;

  @ApiModelProperty(value = "기기타입", position = 3)
  private String type;

  @ApiModelProperty(value = "등록일", position = 4)
  private String regDate;

  @ApiModelProperty(value = "화면분할", position = 3)
  private String displayDiv;

  @ApiModelProperty(value = "우측화면", position = 3)
  private String sideContentsType;

  @ApiModelProperty(value = "아래쪽화면", position = 3)
  private String bottomContentsType;

}
