package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class ManagerAccountModReq {

  @ApiModelProperty(hidden = true)
  private String id;

  @Positive
  @ApiModelProperty(value = "역활 순번", position = 1)
  private Long roleSeq;

  @NotBlank
  @ApiModelProperty(value = "상태 코드", position = 2)
  private String stateCode;

  @ApiModelProperty(value = "메모", position = 3)
  private String memo;

  @ApiModelProperty(value = "이름", position = 3)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 3)
  @EncryptField
  private String phoneNumber;

  @ApiModelProperty(hidden = true)
  private String modId;

}
