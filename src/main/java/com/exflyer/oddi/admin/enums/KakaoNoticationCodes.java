package com.exflyer.oddi.admin.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum KakaoNoticationCodes {

  ODDI_TEST("ex_test001", "개발용 테스트"),
  AUDIT_APPROVAL("oddi_ad_appr", "광고 승인 안내"),
  AUDIT_REJECTION("oddi_ad_deny", "광고 보류 안내"),
  VOC_REPLY("oddi_cs_ans", "문의 답변 알림"),
  ADMIN_PW_CHANGE("oddi_pw_reset", "관리자 비밀번호 초기화"),
  MEMBER_ACCOUNT_DELETE("oddi_account_del", "계정 삭제 안내"),
  MEMBER_ACCOUNT_SLEEP("oddi_accout_sleep", "계정 휴면 알림"),
  PARTNER_ADV_EXPIRED("oddi_ad_exp", "광고 만료 안내"),
  MOBILE_AUTH("oddi_auth", "휴대전화 인증"),
  MEMBER_PW_FIND("oddi_pw_find", "비밀번호 찾기"),
  PAYMENT_CANCEL("oddi_ad_refund", "광고 취소 및 환불")
  ;


  private String code;

  private String name;

  KakaoNoticationCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
