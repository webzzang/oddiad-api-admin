package com.exflyer.oddi.admin.api.cs.faq.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FaqSearchCodes {

  @ApiModelProperty(value = "카테고리코드", position = 1)
  private List<Code> categoryCode;

  @ApiModelProperty(value = "전시상태코드", position = 2)
  private List<Code> expoCode;

  public FaqSearchCodes(List<Code> categoryCode, List<Code> expoCode) {
    this.categoryCode = categoryCode;
    this.expoCode = expoCode;
  }
}
