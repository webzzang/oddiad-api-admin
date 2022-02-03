package com.exflyer.oddi.admin.api.cs.partnerRequest.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartnerRequestSearchCodes {

  @ApiModelProperty(value = "업종 코드", position = 1)
  private List<Code> businessCode;

  public PartnerRequestSearchCodes(List<Code> businessCode) {
    this.businessCode = businessCode;
  }
}
