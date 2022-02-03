package com.exflyer.oddi.admin.api.product.promotion.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionSearchReq extends PagingSearch {

  @ApiModelProperty(value = "프로모션 명", position = 1)
  private String name;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 2)
  private String searchRegStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 3)
  private String searchRegEndDate;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 4)
  private String searchExpiredStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 5)
  private String searchExpiredEndDate;

  @ApiModelProperty(value = "프로모션 타입(PTC001:가입, PTC002:일반)", hidden = true)
  private String type;
}
