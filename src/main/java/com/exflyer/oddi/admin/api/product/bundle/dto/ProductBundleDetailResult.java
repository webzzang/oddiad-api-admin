package com.exflyer.oddi.admin.api.product.bundle.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductBundleDetailResult {

  private ProductBundleResult productBundleResult;
  private List<ProductBundlePartnerResult> productBundlePartnerList;
  private List<ProductBundleFileResult> productBundleFileList;

  public ProductBundleDetailResult(ProductBundleResult productBundleResult, List<ProductBundlePartnerResult> productBundlePartnerList, List<ProductBundleFileResult> productBundleFileList){
    this.productBundleResult = productBundleResult;
    this.productBundlePartnerList = productBundlePartnerList;
    this.productBundleFileList = productBundleFileList;
  }

}
