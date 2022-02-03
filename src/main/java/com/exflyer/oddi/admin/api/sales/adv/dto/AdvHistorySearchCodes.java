package com.exflyer.oddi.admin.api.sales.adv.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvHistorySearchCodes {

  @ApiModelProperty(value = "채널구분코드", position = 1)
  private List<Code> channelTypeCode;

  public AdvHistorySearchCodes(List<Code> channelTypeCode) {
    this.channelTypeCode = channelTypeCode;
  }
}
