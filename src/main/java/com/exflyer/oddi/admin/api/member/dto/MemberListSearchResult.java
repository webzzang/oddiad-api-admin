package com.exflyer.oddi.admin.api.member.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberListSearchResult {

  @ApiModelProperty(value = "아이디", position = 1)
  private String id;

  @ApiModelProperty(value = "이름", position = 2)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 3)
  @DecryptField
  private String phoneNumber;

  @ApiModelProperty(value = "회원 가입 날짜", position = 4)
  private String signupDate;

  @ApiModelProperty(value = "최종 로그인 날짜", position = 5)
  private String loginDate;

  @ApiModelProperty(value = "상태 코드 이름", position = 6)
  private String statusCodeName;

  @ApiModelProperty(value = "이메일", position = 6)
  @DecryptField
  private String email;

}
