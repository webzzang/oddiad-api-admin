package com.exflyer.oddi.admin.api.manager.role.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleConditionResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "이름", position = 2)
  private String name;

  @ApiModelProperty(value = "사용여부(true : 사용, false : 미사용)", position = 3)
  private Boolean usable;

  @ApiModelProperty(value = "생성 날짜(YYYYMMDDHHmmSS)", position = 4)
  private String regDate;

  @ApiModelProperty(value = "변경 날짜(YYYYMMDDHHmmSS)", position = 5)
  private String modDate;

}
