package com.exflyer.oddi.admin.api.contents.livestream.code;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum LiveStreamCodes {

  OPERATION_GROUP_CODE("OPT", "운영여부");


  private String code;

  private String name;

  LiveStreamCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
