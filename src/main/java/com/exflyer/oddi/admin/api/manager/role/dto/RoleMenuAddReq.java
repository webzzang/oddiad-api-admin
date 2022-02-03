package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class RoleMenuAddReq {

  @ApiModelProperty(value = "메뉴 그룹 ID", position = 1)
  private String groupId;

  @ApiModelProperty(value = "메뉴 그룹 이름", position = 1)
  private String groupName;

  private List<RoleSubMenuAddReq> subMenus;


}
