package com.exflyer.oddi.admin.api.product.promotion.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PromotionDetailResult {

  private PromotionBasicResult promotionBasicInfo;
  private List<PromotionCouponResult> promotionCouponList;

  public PromotionDetailResult(PromotionBasicResult promotionBasicInfo, List<PromotionCouponResult> promotionCouponList){
    this.promotionBasicInfo = promotionBasicInfo;
    this.promotionCouponList = promotionCouponList;
  }

}
