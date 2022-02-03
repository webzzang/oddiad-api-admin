package com.exflyer.oddi.admin.api.cs.voc.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VocListSearchReq extends PagingSearch {

  @ApiModelProperty(value = "문의유형코드", position = 1)
  private String inquiryTypeCode;

  @ApiModelProperty(value = "답변상태", position = 2)
  private Boolean answerType;

  @ApiModelProperty(value = "고객ID", position = 3)
  @EncryptField
  private String memberId;

  @ApiModelProperty(value = "제목", position = 4)
  private String title;

  @ApiModelProperty(value = "답변자ID", position = 5)
  @EncryptField
  private String answerRegId;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 6)
  private String searchRegStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 7)
  private String searchRegEndDate;

  @ApiModelProperty(value = "답변일 검색 시작 날짜(YYYYMMDD)", position = 8)
  private String searchAnswerRegStartDate;

  @ApiModelProperty(value = "답변일 검색 종료 날짜(YYYYMMDD)", position = 9)
  private String searchAnswerRegEndDate;

  @ApiModelProperty(value = "답변완료코드", hidden = true)
  private String answerCode;

  @ApiModelProperty(value = "답변대기코드", hidden = true)
  private String noAnswerCode;

}
