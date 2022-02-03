package com.exflyer.oddi.admin.api.terms.codes;

import lombok.Getter;

public enum TermsStatusCodes {

  TEMP("TST001", "임시저장", true),
  USING("TST002", "사용", true),
  NOT_USING("TST003", "폐기", true),
  ;

  @Getter
  private String code;

  @Getter
  private String name;

  @Getter
  private Boolean using;

  TermsStatusCodes(String code, String name, boolean using) {
    this.code = code;
    this.name = name;
    this.using = using;
  }

  public static TermsStatusCodes getByCode(String typeCode) {
    for (TermsStatusCodes statusEnum : TermsStatusCodes.values()) {
      if (statusEnum.code.equalsIgnoreCase(typeCode)) {
        return statusEnum;
      }
    }
    return null;
  }

  public static String getCodeNameByCode(String typeCode) {
    for (TermsStatusCodes statusEnum : TermsStatusCodes.values()) {
      if (statusEnum.code.equalsIgnoreCase(typeCode)) {
        return statusEnum.getName();
      }
    }
    return null;
  }
}
