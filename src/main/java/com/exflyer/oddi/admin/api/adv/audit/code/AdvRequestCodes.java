package com.exflyer.oddi.admin.api.adv.audit.code;

import lombok.Getter;

public enum AdvRequestCodes {

  ADV_APPROVAL("ADT002", 1, "승인"),
  ADV_REJECTION("ADT003", 2, "보류")
  ;


  @Getter
  private final String code;

  @Getter
  private final Integer reqCode;

  @Getter
  private final String message;


  AdvRequestCodes(String code, Integer reqCode, String message) {
    this.code = code;
    this.reqCode = reqCode;
    this.message = message;

  }
}
