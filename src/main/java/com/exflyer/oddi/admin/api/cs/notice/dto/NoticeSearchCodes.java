package com.exflyer.oddi.admin.api.cs.notice.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeSearchCodes {

  @ApiModelProperty(value = "전시상태코드", position = 2)
  private List<Code> expoCode;

  public NoticeSearchCodes( List<Code> expoCode) {
    this.expoCode = expoCode;
  }
}
