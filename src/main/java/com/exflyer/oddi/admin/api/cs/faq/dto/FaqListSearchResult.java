package com.exflyer.oddi.admin.api.cs.faq.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FaqListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "카테고리 명", position = 2)
  private String categoryCodeName;

  @ApiModelProperty(value = "노출여부", position = 3)
  private String expo;

  @ApiModelProperty(value = "노출여부명", position = 4)
  private String expoCodeName;

  @ApiModelProperty(value = "제목", position = 5)
  private String title;

  @ApiModelProperty(value = "등록자 이메일", position = 6)
  @DecryptField
  private String regEmail;

  @ApiModelProperty(value = "등록일", position = 7)
  private String regDate;

}
