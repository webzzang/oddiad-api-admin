package com.exflyer.oddi.admin.api.manager.role.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleCondition extends PagingSearch {

  @ApiModelProperty(value = "이름", position = 2)
  private String name;

  @ApiModelProperty(value = "사용여부(true : 사용, false : 미사용)", position = 3)
  private Boolean usable;

}
