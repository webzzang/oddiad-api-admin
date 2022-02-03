package com.exflyer.oddi.admin.api.partner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OddiZonePartnerSubwayResult {

  @ApiModelProperty(value = "지하철 순번", position = 1)
  private Long subwaySeq;

  @ApiModelProperty(value = "파트너 순번", position = 2)
  private Long partnerSeq;

  @ApiModelProperty(value = "지하철 노선명", position = 3)
  private String subwayName;

  @ApiModelProperty(value = "지하철 노선코드", position = 4)
  private String subwayCode;

}
