package com.exflyer.oddi.admin.api.cs.voc.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VocSearchCodes {

  @ApiModelProperty(value = "문의유형코드", position = 1)
  private List<Code> inquiryTypeCode;

  @ApiModelProperty(value = "답변상태코드", position = 2)
  private List<Code> answerTypeCode;

  public VocSearchCodes(List<Code> inquiryTypeCode, List<Code> answerTypeCode) {
    this.inquiryTypeCode = inquiryTypeCode;
    this.answerTypeCode = answerTypeCode;
  }
}
