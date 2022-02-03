package com.exflyer.oddi.admin.api.device.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchDeviceStateReq extends PagingSearch {

  @ApiModelProperty(value = "기기구분", position = 1)
  private String deviceType;

  @ApiModelProperty(value = "장애구분", position = 2)
  private String deviceLevel;

  @ApiModelProperty(value = "오디존명", position = 3)
  private String mallName;

  @ApiModelProperty(value = "기기이름", position = 4)
  private String deviceName;

  @ApiModelProperty(value = "디바이스 상태 로그 그룹코드", hidden = true)
  private String deviceStateGroupCode;

  @ApiModelProperty(value = "시작", hidden = true)
  private int start;

  @ApiModelProperty(value = "종료", hidden = true)
  private int end;

}
