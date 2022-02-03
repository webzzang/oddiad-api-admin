package com.exflyer.oddi.admin.api.cs.notice.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NoticeListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "전시여부", position = 2)
  private String expo;

  @ApiModelProperty(value = "전시여부명", position = 3)
  private String expoCodeName;

  @ApiModelProperty(value = "제목", position = 4)
  private String title;

  @ApiModelProperty(value = "등록자 이메일", position = 5)
  @DecryptField
  private String regEmail;

  @ApiModelProperty(value = "등록일", position = 6)
  private String regDate;

}
