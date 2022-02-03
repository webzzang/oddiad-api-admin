package com.exflyer.oddi.admin.api.partner.codes;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum BadgeCodes {

  SOLD_OUT("BDG001", "완판"),
  SOLD_OUT_CLOSE("BDG002", "마감임박"),
  HOT("BDG003", "HOT");


  private String code;

  private String name;

  BadgeCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
