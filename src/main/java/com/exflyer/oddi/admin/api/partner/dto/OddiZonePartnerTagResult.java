package com.exflyer.oddi.admin.api.partner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OddiZonePartnerTagResult {

    @ApiModelProperty(value = "파트너 순번", position = 1)
    private Long partnerSeq;

    @ApiModelProperty(value = "태그 순번", position = 2)
    private Long tagSeq;

    @ApiModelProperty(value = "태그", position = 3)
    private String tag;
}
