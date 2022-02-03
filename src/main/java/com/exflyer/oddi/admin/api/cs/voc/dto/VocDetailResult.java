package com.exflyer.oddi.admin.api.cs.voc.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VocDetailResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "회원 id", position = 2)
  @DecryptField
  private String memberId;

  @ApiModelProperty(value = "이름", position = 3)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 4)
  @DecryptField
  private String phoneNumber;

  @ApiModelProperty(value = "제목", position = 5)
  private String title;

  @ApiModelProperty(value = "내용", position = 6)
  private String contents;

  @ApiModelProperty(value = "답변", position = 7)
  private String answer;

  @ApiModelProperty(value = "답변타입", position = 8)
  private String answerType;

  @ApiModelProperty(value = "답변 생성 날짜", position = 9)
  private String answerRegDate;

  @ApiModelProperty(value = "답변 생성 id", position = 10)
  @DecryptField
  private String answerRegId;

  @ApiModelProperty(value = "문의유형 코드명", position = 11)
  private String inquiryTypeCodeName;

  @ApiModelProperty(value = "문의유형 코드", position = 12)
  private String inquiryTypeCode;

  @ApiModelProperty(value = "등록일", position = 13)
  private String regDate;

  @ApiModelProperty(value = "파일순번", position = 14)
  private Long fileSeq;

  @ApiModelProperty(value = "파일명", position = 15)
  private String fileName;

  @ApiModelProperty(value = "회원 이메일", position = 16)
  @DecryptField
  private String email;

}
