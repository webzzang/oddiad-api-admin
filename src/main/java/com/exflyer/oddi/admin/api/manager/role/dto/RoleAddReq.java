package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleAddReq {

  @NotBlank
  @ApiModelProperty(value = "이름", position = 1, required = true)
  private String name;

  @NotNull
  @ApiModelProperty(value = "사용여부(사용:true , 미사용:false)", position = 2, required = true)
  private Boolean usable = false;

  @ApiModelProperty(value = "메모", position = 3)
  private String memo;

  @NotEmpty
  @ApiModelProperty(value = "메뉴", position = 4, required = true)
  private List<RoleMenuAddReq> roleMenus;

  @ApiModelProperty(value = "관리자 그룹 알림수신 리스트", position = 5)
  private List<RoleNotificationAddReq> managerNotificationList;

  @ApiModelProperty(hidden = true)
  private String regId;

}
