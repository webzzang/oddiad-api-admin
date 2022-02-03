package com.exflyer.oddi.admin.api.product.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PromotionBasicResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "프로모션 명", position = 2)
  private String name;

  @ApiModelProperty(value = "할인금액", position = 3)
  private String discountPrice;

  @ApiModelProperty(value = "내용", position = 4)
  private String contents;

  @ApiModelProperty(value = "프로모션종료일", position = 5)
  private String expiredDate;

  @ApiModelProperty(value = "총쿠폰발행매수", position = 6)
  private String couponCount;

  @ApiModelProperty(value = "사용완료 쿠폰매수", position = 7)
  private String useCouponCount;

}
