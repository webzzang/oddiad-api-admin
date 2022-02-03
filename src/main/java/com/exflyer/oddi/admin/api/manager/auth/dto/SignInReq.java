package com.exflyer.oddi.admin.api.manager.auth.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInReq {

  @NotBlank
  @ApiModelProperty(value = "이메일", position = 0)
  @EncryptField
  private String email;

  @NotBlank
  @ApiModelProperty(value = "비밀번호", position = 1)
  private String password;

}
