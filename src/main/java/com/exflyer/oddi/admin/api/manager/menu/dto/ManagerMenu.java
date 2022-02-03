package com.exflyer.oddi.admin.api.manager.menu.dto;


import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class ManagerMenu {

  @ApiModelProperty(value = "그룹 메뉴 코드", position = 0)
  private String groupId;

  @ApiModelProperty(value = "그룹 메뉴 이름", position = 1)
  private String groupName;

  @ApiModelProperty(value = "아이콘", position = 2)
  private String icon;

  @ApiModelProperty(value = "자식 메뉴", position = 3)
  private List<ManagerSubMenu> managerSubMenus;

}
