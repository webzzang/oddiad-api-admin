package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OddiZoneSearchCodes {

  @ApiModelProperty(value = "화면분할코드", position = 1)
  private List<Code> displayDivCode;

  @ApiModelProperty(value = "오른쪽화면코드", position = 2)
  private List<Code> sideDisplayServiceCode;

  @ApiModelProperty(value = "아래쪽화면코드", position = 3)
  private List<Code> bottomDisplayServiceCode;

  public OddiZoneSearchCodes(List<Code> displayDivCode, List<Code> sideDisplayServiceCode, List<Code> bottomDisplayServiceCode) {
    this.displayDivCode = displayDivCode;
    this.sideDisplayServiceCode = sideDisplayServiceCode;
    this.bottomDisplayServiceCode = bottomDisplayServiceCode;
  }
}
