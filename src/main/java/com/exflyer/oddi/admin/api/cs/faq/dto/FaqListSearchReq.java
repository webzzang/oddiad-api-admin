package com.exflyer.oddi.admin.api.cs.faq.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FaqListSearchReq extends PagingSearch {

  @ApiModelProperty(value = "분류", position = 1)
  private String categoryCode;

  @ApiModelProperty(value = "전시상태", position = 2)
  private Boolean expo;

  @ApiModelProperty(value = "제목", position = 3)
  private String title;

}
