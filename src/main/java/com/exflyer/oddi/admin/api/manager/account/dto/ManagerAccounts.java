package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManagerAccounts {

  @ApiModelProperty(value = "id", position = 0)
  @DecryptField
  private String id;

  @ApiModelProperty(value = "이름", position = 1)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 2)
  @DecryptField
  private String phoneNumber;

  @ApiModelProperty(value = "그룹이름", position = 3)
  private String roleName;

  @ApiModelProperty(value = "상태코드 이름", position = 4)
  private String stateCodeName;

  @ApiModelProperty(value = "등록일시(YYYY-MM-DD HH:mm:SS)", position = 5)
  private String regDate;

  @ApiModelProperty(value = "최종 접속 일시(YYYY-MM-DD HH:mm:SS)", position = 6)
  private String loginDate;

}
