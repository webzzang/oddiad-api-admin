package com.exflyer.oddi.admin.api.terms.codes;

import lombok.Getter;

public enum TermsTypeCodes {

  SERVICE("TRM001", "서비스 이용 약관", true),
  PRIVACY("TRM002", "개인 정보 처리 방침", true),
  PROVIDE("TRM003", "제 3자 정보 제공", true),
  MARKETING("TRM004", "개인정보 마케팅 이용 수집 및 동의", true),
  ADV("TRM005", "광고 신청 약관", true),
  ;

  @Getter
  private String code;

  @Getter
  private String name;

  @Getter
  private Boolean using;

  TermsTypeCodes(String code, String name, boolean using) {
    this.code = code;
    this.name = name;
    this.using = using;
  }

  public static TermsTypeCodes getByCode(String typeCode) {
    for (TermsTypeCodes typeEnum : TermsTypeCodes.values()) {
      if (typeEnum.code.equalsIgnoreCase(typeCode)) {
        return typeEnum;
      }
    }
    return null;
  }

  public static String getCodeNameByCode(String typeCode) {
    for (TermsTypeCodes typeEnum : TermsTypeCodes.values()) {
      if (typeEnum.code.equalsIgnoreCase(typeCode)) {
        return typeEnum.getName();
      }
    }
    return null;
  }
}
