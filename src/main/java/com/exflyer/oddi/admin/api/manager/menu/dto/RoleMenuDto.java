package com.exflyer.oddi.admin.api.manager.menu.dto;

import lombok.Data;

@Data
public class RoleMenuDto {


  private String groupId;

  private String groupName;

  private String groupIcon;

  private String menuId;

  private String menuName;

  private String menuIcon;

  private String routerLink;

  private Boolean regAuthority = false;

  private Boolean searchAuthority = false;

  private Boolean modAuthority = false;

  private Boolean delAuthority = false;

}
