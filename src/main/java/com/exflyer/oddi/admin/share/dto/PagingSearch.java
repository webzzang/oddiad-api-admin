package com.exflyer.oddi.admin.share.dto;

import com.google.common.base.CaseFormat;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class PagingSearch {


  @ApiModelProperty(value = "페이지 번호", position = 1, required = true)
  private int pageNo = 1;

  @Max(value = 10000)
  @ApiModelProperty(value = "페이지 row size", position = 2, required = true)
  private int pageSize = 10;

  @ApiModelProperty(value = "정렬 문자열", position = 3)
  private String orderBy;

  public String getOrderBy() {
	  return StringUtils.isBlank(this.orderBy) ? "" : CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.orderBy);
  }
}
