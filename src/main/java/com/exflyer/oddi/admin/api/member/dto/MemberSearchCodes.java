package com.exflyer.oddi.admin.api.member.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSearchCodes {

  @ApiModelProperty(value = "상태코드", position = 0)
  private List<Code> statusCode;

  public MemberSearchCodes(List<Code> memberStatusCode) {
    this.statusCode = memberStatusCode;
  }
}
