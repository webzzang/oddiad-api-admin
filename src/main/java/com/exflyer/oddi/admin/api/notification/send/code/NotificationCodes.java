package com.exflyer.oddi.admin.api.notification.send.code;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum NotificationCodes {

    MANAGER_NAME("", "오디 관리자")
    , NOTIFICATION_ALL_MEMBER("NTC001", "전체 회원")
    , NOTIFICATION_ALL_PARTNER("NTC002", "전체 광고주")
    , NOTIFICATION_ALL_ODDI_PARTNER("NTC003", "전체 오디존 광고주")
    , NOTIFICATION_ALL_SUBWAY_PARTNER("NTC004", "전체 지하철 광고주")
    , NOTIFICATION_ODDI_PARTNER("NTC005", "오디존 광고주")
    , NOTIFICATION_SUBWAY_PARTNER("NTC006", "지하철 광고주")
    , NOTIFICATION_SEARCH_MEMBER("NTC007", "회원 검색")
    , NOTIFICATION_SEARCH_TEL("NTC008", "전화번호 입력")
    , NOTIFICATION_IMMEDIATELY_SEND("MST001", "즉시")
    , NOTIFICATION_RESERVATION_SEND("MST002", "예약")
    , NOTIFICATION_ADV("MTC001", "광고성")
    , NOTIFICATION_INFO("MTC002", "정보성")
    , EXCLUDE_MEMBER_CODE("CTS004", "휴면회원")
  ;


  private String code;

  private String name;

  NotificationCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
