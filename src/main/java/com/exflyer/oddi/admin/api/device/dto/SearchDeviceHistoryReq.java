package com.exflyer.oddi.admin.api.device.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchDeviceHistoryReq extends PagingSearch {

  @ApiModelProperty(value = "동작구분", position = 1)
  private String deviceState;

  @ApiModelProperty(value = "장애구분", position = 2)
  private String deviceLevel;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 3)
  private String searchRegStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 4)
  private String searchRegEndDate;

  @ApiModelProperty(value = "디바이스 코드", position = 5)
  private String deviceId;

  @ApiModelProperty(value = "디바이스 상태 로그 그룹코드", hidden = true)
  private String deviceStateGroupCode;

  @ApiModelProperty(value = "시작", hidden = true)
  private int start;

  @ApiModelProperty(value = "종료", hidden = true)
  private int end;

}
