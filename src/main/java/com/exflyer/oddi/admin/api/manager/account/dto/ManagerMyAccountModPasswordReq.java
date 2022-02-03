package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.annotaions.ValidPassword;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ManagerMyAccountModPasswordReq {

  @ApiModelProperty(hidden = true)
  private String id;

  @ApiModelProperty(value = "이전 비밀번호", position = 1)
  @NotBlank
  private String oldPassword;

  @ApiModelProperty(value = "신규 비밀번호", position = 1)
  @ValidPassword
  @NotBlank
  private String newPassword;

  @ApiModelProperty(hidden = true)
  private String modId;

  @ApiModelProperty(hidden = true)
  private LocalDateTime modDate;

  public boolean equalsOldAndNewPassword() {
    return oldPassword.equals(newPassword);
  }
}
