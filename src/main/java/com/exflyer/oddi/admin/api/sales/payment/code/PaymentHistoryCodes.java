package com.exflyer.oddi.admin.api.sales.payment.code;

import lombok.Getter;

public enum PaymentHistoryCodes {

  INCIS_PAYMENT_TYPE("IPT", null, "결제 구분(결제, 취소)")
  , PAYMENT("IPT001", 1, "결제")
  , CANCEL("IPT002", 0, "취소")
  ;


  @Getter
  private final String code;

  @Getter
  private final Integer value;

  @Getter
  private final String message;


  PaymentHistoryCodes(String code, Integer value, String message) {
    this.code = code;
    this.value = value;
    this.message = message;

  }
}
