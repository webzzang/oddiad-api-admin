package com.exflyer.oddi.admin.api.cs.faq.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FaqDetailResult {

  @ApiModelProperty(value = "순번", position = 1)
  private String seq;

  @ApiModelProperty(value = "카테고리 코드", position = 2)
  private String categoryCode;

  @ApiModelProperty(value = "카테고리 코드명", position = 3)
  private String categoryCodeName;

  @ApiModelProperty(value = "제목", position = 4)
  private String title;

  @ApiModelProperty(value = "노출여부", position = 5)
  private Boolean expo = false;

  @ApiModelProperty(value = "내용", position = 6)
  private String contents;

  @ApiModelProperty(value = "등록자ID", position = 7)
  @DecryptField
  private String regId;

  @ApiModelProperty(value = "등록일", position = 8)
  private String regDate;

  @ApiModelProperty(value = "수정자ID", position = 9)
  @DecryptField
  private String modId;

  @ApiModelProperty(value = "수정일", position = 10)
  private String modDate;

}
