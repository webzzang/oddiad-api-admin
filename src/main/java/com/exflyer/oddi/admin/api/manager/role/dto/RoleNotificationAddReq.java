package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class RoleNotificationAddReq {

  @ApiModelProperty(value = "관리자 그룹 순번", position = 1)
  private Long roleSeq;

  @ApiModelProperty(value = "기기 상태 알림코드", position = 2)
  private String deviceStateCode;

  @ApiModelProperty(value = "기기 상태 알림 수신여부", position = 3)
  private Boolean devicePush;

}
