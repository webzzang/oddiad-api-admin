package com.exflyer.oddi.admin.api.member.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class MemberDetailResult {

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

  @ApiModelProperty(value = "상태 코드", position = 6)
  private String stateCode;

  @ApiModelProperty(value = "상태 코드 이름", position = 7)
  private String stateCodeName;

  @ApiModelProperty(value = "메모", position = 8)
  private String memo;

  @ApiModelProperty(value = "개인 사업자 상호명", position = 9)
  private String pMallName;

  @ApiModelProperty(value = "개인 사업자 대표 이름", position = 10)
  private String pCeo;

  @ApiModelProperty(value = "개인 사업자 사업자 번호", position = 11)
  private String pBusinessLicenseNumber;

  @ApiModelProperty(value = "개인 사업자 사업자 등록증 경로", position = 12)
  private String pBusinessLicenseFilePath;

  @ApiModelProperty(value = "개인 사업자 사업자 등록증 경로", position = 13)
  private Long pBusinessLicenseFileSeq;

  @ApiModelProperty(value = "개인 사업자 등록 날짜", position = 14)
  private String pRegDate;

  @ApiModelProperty(value = "법인 사업자 상호명", position = 15)
  private String cMallName;

  @ApiModelProperty(value = "법인 사업자 대표명", position = 16)
  private String cCeo;

  @ApiModelProperty(value = "법인 사업자 사업자 번호", position = 17)
  private String cBusinessLicenseNumber;

  @ApiModelProperty(value = "법인 사업자 사업자 등록증 경로", position = 18)
  private String cBusinessLicenseFilePath;

  @ApiModelProperty(value = "법인 사업자 사업자 등록증 순번", position = 19)
  private Long cBusinessLicenseFileSeq;

  @ApiModelProperty(value = "법인 사업자 등록 날짜", position = 20)
  private String cRegDate;

  @ApiModelProperty(value = "이메일", position = 21)
  @DecryptField
  private String email;

  private List<MemberTermsListResult> memberTermsList;
}
