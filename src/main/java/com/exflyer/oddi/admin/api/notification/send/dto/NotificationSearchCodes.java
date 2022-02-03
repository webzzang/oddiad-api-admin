package com.exflyer.oddi.admin.api.notification.send.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationSearchCodes {

  @ApiModelProperty(value = "알림타킷코드", position = 1)
  private List<Code> notificationTypeCode;

  @ApiModelProperty(value = "메세지 발송구분", position = 2)
  private List<Code> messageSendCode;

  @ApiModelProperty(value = "메세지 타입구분", position = 3)
  private List<Code> messageTypeCode;

  public NotificationSearchCodes(List<Code> notificationTypeCode, List<Code> messageSendCode, List<Code> messageTypeCode) {
    this.notificationTypeCode = notificationTypeCode;
    this.messageSendCode = messageSendCode;
    this.messageTypeCode = messageTypeCode;
  }
}
