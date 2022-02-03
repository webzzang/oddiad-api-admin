package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleSubMenuAddReq {

  @ApiModelProperty(value = "메뉴 id", position = 1)
  private String menuId;

  @ApiModelProperty(value = "쓰기 권한 여부", position = 1)
  private Boolean regAuthority;

  @ApiModelProperty(value = "조회 권한 여부", position = 1)
  private Boolean searchAuthority;

  @ApiModelProperty(value = "수정 권한 여부", position = 1)
  private Boolean modAuthority;

  @ApiModelProperty(value = "삭제 권한 여부", position = 1)
  private Boolean delAuthority;

}
