package com.exflyer.oddi.admin.api.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberTermsListResult {

  @ApiModelProperty(value = "약관제목", position = 1)
  private String adminTitle;

  @ApiModelProperty(value = "약관코드명", position = 2)
  private String frontTitle;

  @ApiModelProperty(value = "동의여부", position = 3)
  private String termsName;

  @ApiModelProperty(value = "버전", position = 4)
  private String version;

  @ApiModelProperty(value = "동의필수", position = 5)
  private String required;

  @ApiModelProperty(value = "동의필수명", position = 5)
  private String requiredName;

}
