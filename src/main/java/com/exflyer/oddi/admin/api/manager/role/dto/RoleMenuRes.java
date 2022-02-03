package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuRes {

  @ApiModelProperty(value = "메뉴 그룹id", position = 1)
  private String groupId;

  @ApiModelProperty(value = "메뉴 그룹이름", position = 2)
  private String groupName;

  @ApiModelProperty(value = "메뉴id", position = 3)
  private String menuId;

  @ApiModelProperty(value = "메뉴 이름", position = 4)
  private String menuName;

  @ApiModelProperty(value = "생성권한(가능:true , 불가능:false)", position = 5)
  private Boolean regAuthority = false;

  @ApiModelProperty(value = "조회권한(가능:true , 불가능:false)", position = 6)
  private Boolean searchAuthority = false;

  @ApiModelProperty(value = "수정권한(가능:true , 불가능:false)", position = 7)
  private Boolean modAuthority = false;

  @ApiModelProperty(value = "삭제권한(가능:true , 불가능:false)", position = 8)
  private Boolean delAuthority = false;

  public RoleMenuRes(String menuId, boolean created, boolean read, boolean update, boolean delete) {
    this.menuId = menuId;
    this.regAuthority = created;
    this.searchAuthority = read;
    this.modAuthority = update;
    this.delAuthority = delete;
  }
}
