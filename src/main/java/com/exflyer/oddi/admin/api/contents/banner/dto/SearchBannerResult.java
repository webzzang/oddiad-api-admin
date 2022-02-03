package com.exflyer.oddi.admin.api.contents.banner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchBannerResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "배너명", position = 2)
  private String name;

  @ApiModelProperty(value = "노출시작일", position = 3)
  private String expoStartDate;

  @ApiModelProperty(value = "노출종료일", position = 4)
  private String expoEndDate;

  @ApiModelProperty(value = "등록일", position = 5)
  private String regDate;

  @ApiModelProperty(value = "수정일", position = 6)
  private String modDate;

}
