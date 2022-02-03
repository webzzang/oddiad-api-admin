package com.exflyer.oddi.admin.api.manager.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminAuth {

  @ApiModelProperty(hidden = true)
  private String id;

  public AdminAuth(String id) {
    this.id = id;
  }

}
