package com.exflyer.oddi.admin.api.product.promotion.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PromotionCouponList {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "프로모션 순번", position = 2)
  private Long promotionSeq;

  @ApiModelProperty(value = "쿠폰코드", position = 3)
  private String couponCode;

  @ApiModelProperty(value = "사용여부코드", position = 4)
  private String usable;

  @ApiModelProperty(value = "사용여부코드명", position = 5)
  private String usableName;

  @ApiModelProperty(value = "사용고객 ID", position = 6)
  @DecryptField
  private String memberId;

  @ApiModelProperty(value = "사용고객 email", position = 6)
  @DecryptField
  private String email;

  @ApiModelProperty(value = "사용일시", position = 7)
  private String usingDate;

  @ApiModelProperty(value = "발행일시", position = 8)
  private String regDate;

  @ApiModelProperty(value = "만료일시", position = 9)
  private String expiredDate;

  @ApiModelProperty(value = "순서", position = 10)
  private Integer rownum;

}
