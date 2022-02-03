package com.exflyer.oddi.admin.api.manager.role.dto;

import com.exflyer.oddi.admin.models.ManagerNotification;
import com.exflyer.oddi.admin.models.Role;
import io.swagger.annotations.ApiModelProperty;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class RoleDetail {

  @ApiModelProperty(value = "순번", position = 0)
  private Long seq;

  @ApiModelProperty(value = "이름", position = 1)
  private String name = "";

  @ApiModelProperty(value = "사용여부(사용:true , 미사용:false)", position = 2)
  private Boolean usable = true;

  @ApiModelProperty(value = "메모", position = 3)
  private String memo = "";

  @ApiModelProperty(value = "등록날짜", position = 4)
  private String regDate = "";

  @ApiModelProperty(value = "수정날짜", position = 5)
  private String modDate = "";

  @ApiModelProperty(value = "메뉴", position = 6)
  private List<RoleMenuWithSubMenu> roleMenus;

  @ApiModelProperty(value = "관리자 그룹 알림수신 리스트", position = 5)
  private List<RoleNotificationRes> managerNotificationList;

  public RoleDetail(Role role) {
    this.seq = role.getSeq();
    this.name = role.getName();
    this.usable = role.isUsable();
    this.regDate = StringUtils.defaultString(role.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")), "");
    if (role.getModDate() != null) {
      this.modDate = role.getModDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }else{
      this.modDate = null;
    }
    this.memo = role.getMemo();
  }
}
