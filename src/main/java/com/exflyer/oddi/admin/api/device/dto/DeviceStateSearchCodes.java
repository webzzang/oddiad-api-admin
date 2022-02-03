package com.exflyer.oddi.admin.api.device.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceStateSearchCodes {

  @ApiModelProperty(value = "디바이스 구분", position = 1)
  private List<Code> deviceTypeCode;

  @ApiModelProperty(value = "디바이스 상태 로그", position = 2)
  private List<Code> deviceStateCode;

  @ApiModelProperty(value = "디바이스 상태 등급(정상, 주의, 심각 등)", position = 3)
  private List<Code> deviceLevelCode;

  public DeviceStateSearchCodes(List<Code> deviceTypeCode, List<Code> deviceStateCode, List<Code> deviceLevelCode) {
    this.deviceTypeCode = deviceTypeCode;
    this.deviceStateCode = deviceStateCode;
    this.deviceLevelCode = deviceLevelCode;
  }
}
