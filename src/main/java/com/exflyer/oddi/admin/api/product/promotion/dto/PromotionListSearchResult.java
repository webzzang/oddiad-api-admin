package com.exflyer.oddi.admin.api.product.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PromotionListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "프로모션 명", position = 2)
  private String name;

  @ApiModelProperty(value = "할인금액", position = 3)
  private String discountPrice;

  @ApiModelProperty(value = "쿠폰발행매수", position = 4)
  private String couponCount;

  @ApiModelProperty(value = "쿠폰발행일시", position = 6)
  private String regDate;

  @ApiModelProperty(value = "프로모션종료일", position = 7)
  private String expiredDate;

}
