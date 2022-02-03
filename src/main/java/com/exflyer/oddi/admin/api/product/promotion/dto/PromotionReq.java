package com.exflyer.oddi.admin.api.product.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PromotionReq {

  @ApiModelProperty(value = "프로모션 명", position = 1)
  private String name;

  @ApiModelProperty(value = "할인금액", position = 2)
  private Integer discountPrice;

  @ApiModelProperty(value = "할인구분(PDT001:정액)", position = 3)
  private String discountType;

  @ApiModelProperty(value = "내용", position = 4)
  private String contents;

  @ApiModelProperty(value = "프로모션종료일", position = 5)
  private String expiredDate;

  @ApiModelProperty(value = "쿠폰 발행매수", position = 6)
  private Integer couponCnt;

  @ApiModelProperty(value = "사용여부", position = 7)
  private Boolean usable = true;

  @ApiModelProperty(value = "가입자 쿠폰사용코드", hidden = true)
  private String memberCouponCode;

  @ApiModelProperty(value = "가입자 쿠폰 가입후 만료일자", hidden = true)
  private Integer memberCouponExpiredDay;

  @ApiModelProperty(value = "프로모션 타입(PTC001:가입, PTC002:일반)", hidden = true)
  private String type;

  @ApiModelProperty(value = "프로모션 순번", hidden = true)
  private Long seq;

  @ApiModelProperty(value = "쿠폰발행일시", hidden = true)
  private LocalDateTime regDate;

  @ApiModelProperty(value = "등록 ID", hidden = true)
  private String regId;

  @ApiModelProperty(value = "수정일", hidden = true)
  private LocalDateTime modDate;

  @ApiModelProperty(value = "수정 ID", hidden = true)
  private String modId;

  @ApiModelProperty(value = "쿠폰 코드", hidden = true)
  private String couponCode;

}
