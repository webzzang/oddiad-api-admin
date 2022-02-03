package com.exflyer.oddi.admin.api.cs.voc.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VocListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private String seq;

  @ApiModelProperty(value = "회원 id", position = 2)
  @DecryptField
  private String memberId;

  @ApiModelProperty(value = "이메일", position = 2)
  @DecryptField
  private String email;

  @ApiModelProperty(value = "문의유형 코드명", position = 2)
  private String inquiryTypeCodeName;

  @ApiModelProperty(value = "답변상태", position = 2)
  private String answerType;

  @ApiModelProperty(value = "제목", position = 3)
  private String title;

  @ApiModelProperty(value = "답변", position = 5)
  private String answer;

  @ApiModelProperty(value = "답변 생성 날짜", position = 8)
  private String answerRegDate;

  @ApiModelProperty(value = "답변 생성자 이메일", position = 7)
  @DecryptField
  private String answerRegEmail;

  @ApiModelProperty(value = "등록일", position = 8)
  private String regDate;

}
