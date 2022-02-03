package com.exflyer.oddi.admin.api.member.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberModReq {

  @ApiModelProperty(hidden = true)
  private String id;

  @NotBlank
  @ApiModelProperty(value = "상태 코드", position = 1)
  private String stateCode;

  @ApiModelProperty(value = "메모", position = 2)
  private String memo;

  @ApiModelProperty(hidden = true)
  private String modId;

  @ApiModelProperty(hidden = true)
  private LocalDateTime modDate;

}
