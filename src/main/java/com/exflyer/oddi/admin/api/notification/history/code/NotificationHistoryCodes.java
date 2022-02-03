package com.exflyer.oddi.admin.api.notification.history.code;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum NotificationHistoryCodes {

    NOTIFICATION_SEND_TYPE_AUTO("NAT001", "자동")
    , NOTIFICATION_SEND_TYPE_MANUAL("NAT002", "수동")
  ;


  private String code;

  private String name;

  NotificationHistoryCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
