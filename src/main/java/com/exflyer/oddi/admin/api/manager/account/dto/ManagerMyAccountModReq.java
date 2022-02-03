package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ManagerMyAccountModReq {

  @ApiModelProperty(value = "이름", position = 0)
  @NotBlank
  private String name;

  @ApiModelProperty(value = "전화번호", position = 1)
  @NotBlank
  @EncryptField
  private String phoneNumber;

  @ApiModelProperty(hidden = true)
  private String id;

  @ApiModelProperty(hidden = true)
  private String modId;

  @ApiModelProperty(hidden = true)
  private LocalDateTime modDate;

}
