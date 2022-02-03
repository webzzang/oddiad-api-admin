package com.exflyer.oddi.admin.enums;

import lombok.Getter;

public enum ApiResponseCodes {

  SUCCESS(200, "000", "success"),
  NOT_DUPLICATE(200, "000", "중복 되지 않은 정보 입니다."),
  BAD_REQUEST(400, "001", "잘못된 요청 입니다."),
  NOT_FOUND(404, "002", "정보를 찾을 수 없습니다."),
  TOKEN_EXPIRED(401, "003", "토큰이 만료 되었습니다."),
  AUTHENTIFICATION(401, "004", "인증 정보가 잘못 되었습니다."),
  STOP_MANAGER_STATE(401, "017", "계정이 정지상태입니다."),
  DORMANT_MANAGER_STATE(401, "018", "계정이 휴면상태입니다."),
  PASSWORD_FAIL_OVER_COUNT_MANAGER(401, "019", "비밀번호 5회 오류로 인한 로그인 차단상태입니다."),
  AUTHENTIFICATION_ACCOUNT(401, "020", "등록되지 않은 아이디거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.(5회 이상 잘못 입력시, 로그인이 제한됩니다.)"),
  MISS_MATCH(400, "005", "정보가 일치 하지 않습니다."),
  DUPLICATE(400, "007", "중복된 정보 입니다."),
  FORBIDDEN(403, "008", "접근 권한이 없습니다."),
  INIT_PASSWORD(400, "009", "비밀번호 재설정이 필요 합니다."),
  NEED_TO_PASSWORD_CHANGED(200, "011", "비밀번호 변경이 필요 합니다."),
  PASSWORD_CHANGE_DAY_OVER(200, "012", "비밀번호 변경 주기가 지났습니다."),
  EXPIRED_REQ_TIME(400, "013", "요청 시간이 초과 되었습니다."),
  DUPLICATE_DEVICE(400, "014", "중복된 장비가 이미 존재합니다."),
  ACCURATE_NOT_DEVICE(400, "015", "매체코드를 정확히 입력해주세요."),
  DUPLICATE_SITE_POPUP_EXPO(400, "016", "사이트 팝업의 노출기간이 중복되었습니다."),

  AWS_S3_FAIL(500, "900", "AWS S3 오류"),
  INTERNAL(500, "999", "관리자에게 문의 하세요"),
  KAKAO_NOTI_ERROR(500, "500", "카카오 알림톡 전송이 실패하였습니다.")
  ;


  @Getter
  private final int status;

  @Getter
  private final String code;

  @Getter
  private final String message;


  ApiResponseCodes(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;

  }
}
