package com.exflyer.oddi.admin.api.product.bundle.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductBundleSearchReq extends PagingSearch {


  @ApiModelProperty(value = "운영여부", position = 1)
  private String operation;

  @ApiModelProperty(value = "상품이름", position = 2)
  private String name;

  @ApiModelProperty(value = "오디존이름", position = 3)
  private String mallName;


}
