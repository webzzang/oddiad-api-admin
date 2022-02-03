package com.exflyer.oddi.admin.api.cs.voc.code;

import lombok.Getter;

public enum VocCodes {

  ANSWER("RPT001", "답변대기"),
  NO_ANSWER("RPT002", "답변완료")
  ;


  @Getter
  private final String code;

  @Getter
  private final String message;


  VocCodes(String code, String message) {
    this.code = code;
    this.message = message;

  }
}
