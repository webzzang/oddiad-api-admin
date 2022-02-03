package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RoleMenuWithSubMenu {

  @ApiModelProperty(value = "메뉴 그룹id", position = 1)
  private String groupId;

  @ApiModelProperty(value = "메뉴 그룹이름", position = 2)
  private String groupName;

  @ApiModelProperty(value = "권한 메뉴", position = 2)
  private List<RoleMenuRes> subMenus = new ArrayList<>();

}
