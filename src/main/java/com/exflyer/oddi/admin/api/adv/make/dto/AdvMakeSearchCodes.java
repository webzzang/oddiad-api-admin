package com.exflyer.oddi.admin.api.adv.make.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvMakeSearchCodes {

  @ApiModelProperty(value = "채널코드", position = 1)
  private List<Code> advChannelCode;

  @ApiModelProperty(value = "화면분할코드", position = 2)
  private List<Code> displayDivCode;

  @ApiModelProperty(value = "오른쪽화면코드", position = 3)
  private List<Code> sideDisplayServiceCode;

  @ApiModelProperty(value = "하단화면코드", position = 4)
  private List<Code> bottomDisplayServiceCode;


  public AdvMakeSearchCodes(List<Code> advChannelCode, List<Code> displayDivCode, List<Code> sideDisplayServiceCode, List<Code> bottomDisplayServiceCode) {
    this.advChannelCode = advChannelCode;
    this.displayDivCode = displayDivCode;
    this.sideDisplayServiceCode = sideDisplayServiceCode;
    this.bottomDisplayServiceCode = bottomDisplayServiceCode;
  }
}
