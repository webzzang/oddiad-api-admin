package com.exflyer.oddi.admin.api.notification.history.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchHistoryReq extends PagingSearch {

  @ApiModelProperty(value = "발송타입", hidden = true)
  private String messageSendTypeCode;

  @ApiModelProperty(value = "발송타입 값(val)", position = 1)
  private String messageSendTypeVal;

  @ApiModelProperty(value = "대상구분", position = 2)
  private String targetTypeCode;

  @ApiModelProperty(value = "발송일 검색 시작 날짜(YYYYMMDD)", position = 3)
  private String searchRegStartDate;

  @ApiModelProperty(value = "발송일 검색 종료 날짜(YYYYMMDD)", position = 4)
  private String searchRegEndDate;

  @ApiModelProperty(value = "내용", position = 5)
  private String contents;

  @ApiModelProperty(value = "고객이름", position = 6)
  private String receiveName;

  @ApiModelProperty(value = "전화번호", position = 7)
  private String receivePhoneNumber;

}
