package com.exflyer.oddi.admin.api.product.promotion.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PromotionCouponReq extends PagingSearch {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "쿠폰코드", position = 2)
  private String couponCode;

  @ApiModelProperty(value = "사용여부코드", position = 3)
  private Boolean usable;

  @ApiModelProperty(value = "사용고객 ID", position = 4)
  @EncryptField
  private String memberId;

  @ApiModelProperty(value = "사용고객 email", position = 4)
  @EncryptField
  private String email;

  @ApiModelProperty(value = "사용일시(start - YYYYMMDD)", position = 5)
  private String searchUsingStartDate;

  @ApiModelProperty(value = "사용일시(end - YYYYMMDD)", position = 6)
  private String searchUsingEndDate;

  @ApiModelProperty(value = "쿠폰사용그룹코드", hidden = true)
  private String couponUsableGroupCode;

  @ApiModelProperty(value = "시작", hidden = true)
  private int start;

  @ApiModelProperty(value = "종료", hidden = true)
  private int end;

}
