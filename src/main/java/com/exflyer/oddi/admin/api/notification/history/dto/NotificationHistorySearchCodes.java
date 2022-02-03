package com.exflyer.oddi.admin.api.notification.history.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationHistorySearchCodes {

  @ApiModelProperty(value = "대상구분", position = 1)
  private List<Code> targetTypeCode;

  @ApiModelProperty(value = "발송타입", position = 2)
  private List<Code> messageSendTypeCode;

  public NotificationHistorySearchCodes(List<Code> targetTypeCode, List<Code> messageSendTypeCode) {
    this.targetTypeCode = targetTypeCode;
    this.messageSendTypeCode = messageSendTypeCode;
  }
}
