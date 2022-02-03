package com.exflyer.oddi.admin.api.product.bundle.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductBundlePartnerResult {

    @ApiModelProperty(value = "순번", position = 1)
    private String seq;

    @ApiModelProperty(value = "파트너명", position = 2)
    private String mallName;

    @ApiModelProperty(value = "파트너순번", position = 3)
    private String partnerSeq;
}
