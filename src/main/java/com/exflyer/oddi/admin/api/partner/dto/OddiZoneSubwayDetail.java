package com.exflyer.oddi.admin.api.partner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OddiZoneSubwayDetail {

  @ApiModelProperty(value = "지하철 운영시간(start - 6자리)", position = 1)
  private String operationStartTime;

  @ApiModelProperty(value = "지하철 운영시간(end - 6자리)", position = 2)
  private String operationEndTime;



}
