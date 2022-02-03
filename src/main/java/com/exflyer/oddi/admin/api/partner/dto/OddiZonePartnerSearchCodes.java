package com.exflyer.oddi.admin.api.partner.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OddiZonePartnerSearchCodes {

  @ApiModelProperty(value = "운영여부 코드", position = 1)
  private List<Code> operationCode;

  @ApiModelProperty(value = "배지 코드", position = 2)
  private List<Code> badgeCode;

  @ApiModelProperty(value = "노출 코드", position = 3)
  private List<Code> showCode;

  @ApiModelProperty(value = "지하철노선 코드", position = 4)
  private List<Code> subwayLineCode;

  public OddiZonePartnerSearchCodes(List<Code> operationCode, List<Code> badgeCode, List<Code> showCode, List<Code> subwayLineCode) {
    this.operationCode = operationCode;
    this.badgeCode = badgeCode;
    this.showCode = showCode;
    this.subwayLineCode = subwayLineCode;
  }
}
