package com.exflyer.oddi.admin.api.device.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchDeviceHistoryResult {

  @ApiModelProperty(value = "일시", position = 1)
  private String regDate;

  @ApiModelProperty(value = "동작코드", position = 2)
  private String deviceState;

  @ApiModelProperty(value = "동작", position = 3)
  private String deviceStateName;

  @ApiModelProperty(value = "장애", position = 4)
  private String deviceLevelName;


}
