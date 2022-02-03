package com.exflyer.oddi.admin.api.manager.account.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class ManagerAccountRegReq {

  @NotBlank
  @ApiModelProperty(value = "ID(Email)", position = 0)
  @EncryptField
  private String id;

  @NotBlank
  @ApiModelProperty(value = "이름", position = 1)
  private String name;

  @Positive
  @ApiModelProperty(value = "소속그룹 수번", position = 2)
  private Long roleSeq;


  @ApiModelProperty(value = "전화번호", position = 4)
  @EncryptField
  private String phoneNumber;

  @NotBlank
  @ApiModelProperty(value = "상태 코드", position = 4)
  private String stateCode;

  @ApiModelProperty(value = "메모", position = 8)
  private String memo;

  @ApiModelProperty(hidden = true)
  private String regManagerId;
}
