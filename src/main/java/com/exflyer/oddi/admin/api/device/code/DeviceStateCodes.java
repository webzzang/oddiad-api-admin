package com.exflyer.oddi.admin.api.device.code;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum DeviceStateCodes {

  DEVICE_STATE_CODES("DSL", "디바이스 상태 로그 그룹코드");


  private String code;
  private String name;

  DeviceStateCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
