package com.exflyer.oddi.admin.api.product.promotion.code;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum PromotionCodes {

  MEMBER_PROMOTION("PTC001", "가입"),
  NORMAL_PROMOTION("PTC002", "일반"),
  MONEY_DISCOUNT("PDT001", "정액"),
  COUPON_USABLE("CUT", "쿠폰사용여부");


  private String code;

  private String name;

  PromotionCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
