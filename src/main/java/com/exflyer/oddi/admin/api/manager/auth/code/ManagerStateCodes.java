package com.exflyer.oddi.admin.api.manager.auth.code;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;


@JsonFormat(shape = Shape.OBJECT)
@Getter
public enum ManagerStateCodes {

  NORMAL_MANAGER_STATE("MSC001", "사용")
  , STOP_MANAGER_STATE("MSC002", "정지")
  , DORMANT_MANAGER_STATE("MSC003", "휴면")
  ;


  private String code;

  private String name;

  ManagerStateCodes(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
