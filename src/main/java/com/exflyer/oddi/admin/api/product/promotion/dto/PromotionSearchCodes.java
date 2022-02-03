package com.exflyer.oddi.admin.api.product.promotion.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromotionSearchCodes {

  @ApiModelProperty(value = "프로모션 구분코드", position = 1)
  private List<Code> promotionTypeCode;

  @ApiModelProperty(value = "할인구분 코드", position = 2)
  private List<Code> discountTypeCode;

  @ApiModelProperty(value = "사용자쿠폰코드", position = 3)
  private List<Code> memberCouponCode;

  @ApiModelProperty(value = "쿠폰사용여부코드", position = 3)
  private List<Code> couponUsableCode;

  public PromotionSearchCodes(List<Code> promotionTypeCode, List<Code> discountTypeCode, List<Code> memberCouponCode, List<Code> couponUsableCode) {
    this.promotionTypeCode = promotionTypeCode;
    this.discountTypeCode = discountTypeCode;
    this.memberCouponCode = memberCouponCode;
    this.couponUsableCode = couponUsableCode;
  }
}
