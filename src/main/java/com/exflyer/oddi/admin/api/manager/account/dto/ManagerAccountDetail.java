package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManagerAccountDetail {

  @ApiModelProperty(value = "이름", position = 1)
  private String name;

  @ApiModelProperty(value = "소속그룹 수번", position = 2)
  private String roleSeq;

  @ApiModelProperty(value = "id", position = 3)
  @DecryptField
  private String id;

  @ApiModelProperty(value = "전화번호", position = 4)
  @DecryptField
  private String phoneNumber;

  @ApiModelProperty(value = "그룹이름", position = 5)
  private String roleName;

  @ApiModelProperty(value = "상태코드", position = 6)
  private String stateCode;

  @ApiModelProperty(value = "상태코드 이름", position = 7)
  private String stateCodeName;

  @ApiModelProperty(value = "메모", position = 8)
  private String memo;

  @ApiModelProperty(value = "등록일시", position = 9)
  private String regDate;

  @ApiModelProperty(value = "최종 접속 날짜", position = 10)
  private String loginDate;
}
