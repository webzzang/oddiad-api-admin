package com.exflyer.oddi.admin.api.cs.notice.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeListSearchReq extends PagingSearch {

  @ApiModelProperty(value = "전시상태", position = 1)
  private Boolean expo;

  @ApiModelProperty(value = "제목", position = 2)
  private String title;

  @ApiModelProperty(value = "등록자ID", position = 3)
  @EncryptField
  private String regId;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 4)
  private String searchRegStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 5)
  private String searchRegEndDate;

}
