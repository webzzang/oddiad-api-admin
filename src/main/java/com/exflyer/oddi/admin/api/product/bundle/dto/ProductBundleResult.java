package com.exflyer.oddi.admin.api.product.bundle.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class ProductBundleResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "상품 이름", position = 2)
  private String name;

  @ApiModelProperty(value = "상품 가격", position = 3)
  private String price;

  @ApiModelProperty(value = "상품 가격", position = 4)
  private String priceLabel;

  @ApiModelProperty(value = "소개글", position = 5)
  private String description;

  @ApiModelProperty(value = "광고 사례 노출", position = 6)
  private Boolean advCaseExpo = false;

  @ApiModelProperty(value = "운영 여부", position = 7)
  private Boolean operation = false;

  @ApiModelProperty(value = "메모", position = 8)
  private String memo;

  @ApiModelProperty(value = "등록 일시", position = 9)
  private String regDate;

  @ApiModelProperty(value = "배지코드", position = 10)
  private String badgeCode;
}
