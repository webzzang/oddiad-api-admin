package com.exflyer.oddi.admin.api.product.promotion.dto;

import com.exflyer.oddi.admin.models.Promotion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromotionMemberCouponResult {

  @ApiModelProperty(value = "쿠폰이름", position = 1)
  private String name;

  @ApiModelProperty(value = "할인타입", position = 2)
  private String discountType;

  @ApiModelProperty(value = "할인금액", position = 3)
  private Integer discountPrice;

  @ApiModelProperty(value = "가입자 쿠폰사용기한 코드", position = 4)
  private String memberCouponCode;

  @ApiModelProperty(value = "사용여부", position = 5)
  private Boolean usable;

  @ApiModelProperty(value = "사용기한", position = 6)
  private Integer memberCouponExpiredDay;


  public PromotionMemberCouponResult(Promotion param) {
    this.name = param.getName();
    this.discountType = param.getDiscountType();
    this.discountPrice = param.getDiscountPrice();
    this.usable = param.getUsable();
    this.memberCouponCode = param.getMemberCouponCode();
    this.memberCouponExpiredDay = param.getMemberCouponExpiredDay();
  }

}
