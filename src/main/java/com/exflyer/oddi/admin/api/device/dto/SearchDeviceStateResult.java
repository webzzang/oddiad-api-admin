package com.exflyer.oddi.admin.api.device.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchDeviceStateResult {

  @ApiModelProperty(value = "오디존명", position = 1)
  private String mallName;

  @ApiModelProperty(value = "매체코드", position = 2)
  private String deviceId;

  @ApiModelProperty(value = "기기이름", position = 3)
  private String deviceName;

  @ApiModelProperty(value = "기기구분", position = 4)
  private String deviceTypeName;

  @ApiModelProperty(value = "최종동작", position = 5)
  private String deviceStateName;

  @ApiModelProperty(value = "장애", position = 6)
  private String deviceLevelName;

  @ApiModelProperty(value = "업데이트일시", position = 7)
  private String modDate;

}
