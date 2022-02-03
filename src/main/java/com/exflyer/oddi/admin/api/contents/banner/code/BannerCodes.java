package com.exflyer.oddi.admin.api.contents.banner.code;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum BannerCodes {

  WEB_BANNER_TYPE("BNT001", "web")
  ,BANNER_TOP_LOCATION("BLT001", "상단")
  ,BANNER_BOTTOM_LOCATION("BLT002", "하단")
  ,BANNER_SIDE_LOCATION("BLT003", "우측")
  ,SITE_POPUP("BLT004", "사이트팝업")
  ;


  private String code;

  private String name;

  BannerCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
