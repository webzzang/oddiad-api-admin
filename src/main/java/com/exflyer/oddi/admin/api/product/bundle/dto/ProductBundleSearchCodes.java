package com.exflyer.oddi.admin.api.product.bundle.dto;

import com.exflyer.oddi.admin.models.Code;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBundleSearchCodes {

  @ApiModelProperty(value = "광고사례노출코드", position = 1)
  private List<Code> usableCode;

  @ApiModelProperty(value = "운영여부코드", position = 2)
  private List<Code> operationCode;

  @ApiModelProperty(value = "배지코드", position = 3)
  private List<Code> badgeCode;

  public ProductBundleSearchCodes(List<Code> usableCode, List<Code> operationCode, List<Code> badgeCode) {
    this.usableCode = usableCode;
    this.operationCode = operationCode;
    this.badgeCode = badgeCode;
  }
}
