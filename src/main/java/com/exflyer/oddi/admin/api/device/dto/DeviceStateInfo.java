package com.exflyer.oddi.admin.api.device.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceStateInfo {

  @ApiModelProperty(value = "기기이름", position = 1)
  private String deviceName;

  @ApiModelProperty(value = "매체코드", position = 2)
  private String deviceId;

  @ApiModelProperty(value = "기기구분", position = 3)
  private String deviceTypeName;

  @ApiModelProperty(value = "오디존명", position = 4)
  private String mallName;

}
