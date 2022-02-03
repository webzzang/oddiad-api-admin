package com.exflyer.oddi.admin.api.manager.menu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerSubMenu {

  @ApiModelProperty(value = "코드", position = 1)
  private String menuId;

  @ApiModelProperty(value = "이름", position = 2)
  private String menuName;

  @ApiModelProperty(value = "router link", position = 3)
  private String routerLink;

  @ApiModelProperty(value = "생성권한(가능:true , 불가능:false)", position = 5)
  private Boolean regAuthority = false;

  @ApiModelProperty(value = "조회권한(가능:true , 불가능:false)", position = 6)
  private Boolean searchAuthority = false;

  @ApiModelProperty(value = "수정권한(가능:true , 불가능:false)", position = 7)
  private Boolean modAuthority = false;

  @ApiModelProperty(value = "삭제권한(가능:true , 불가능:false)", position = 8)
  private Boolean delAuthority = false;




  public ManagerSubMenu(RoleMenuDto roleMenuDto) {
    this.menuId = roleMenuDto.getMenuId();
    this.menuName = roleMenuDto.getMenuName();
    this.routerLink = StringUtils.defaultString(roleMenuDto.getRouterLink(), "");
    this.regAuthority = roleMenuDto.getRegAuthority();
    this.searchAuthority = roleMenuDto.getSearchAuthority();
    this.modAuthority = roleMenuDto.getModAuthority();
    this.delAuthority = roleMenuDto.getDelAuthority();
  }

}
