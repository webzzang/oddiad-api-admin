package com.exflyer.oddi.admin.api.product.bundle.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductBundleListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "상품이름", position = 2)
  private String name;

  @ApiModelProperty(value = "구성", position = 3)
  private String mallNames;

  @ApiModelProperty(value = "가격", position = 4)
  private String price;

  @ApiModelProperty(value = "운영여부", position = 5)
  private Boolean operation;

  @ApiModelProperty(value = "등록일시", position = 6)
  private String regDate;

  @ApiModelProperty(value = "최종수정일시", position = 7)
  private String modDate;

  @ApiModelProperty(value = "운영여부명", position = 8)
  private String operationName;

}
