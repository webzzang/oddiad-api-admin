package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleNotificationRes {

  @ApiModelProperty(value = "관리자 그룹 순번", position = 1)
  private Long roleSeq;

  @ApiModelProperty(value = "기기 상태 알림코드", position = 2)
  private String deviceStateCode;

  @ApiModelProperty(value = "기기 상태 알림코드명", position = 3)
  private String deviceStateName;

  @ApiModelProperty(value = "기기 상태 알림 수신여부", position = 4)
  private Boolean devicePush;

}
