package com.exflyer.oddi.admin.api.partner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OddiZonePartnerFileResult {

    @ApiModelProperty(value = "파트너 순번", position = 1)
    private Long partnerSeq;

    @ApiModelProperty(value = "파일순번", position = 2)
    private Long fileSeq;

    @ApiModelProperty(value = "파일패스", position = 3)
    private String path;

    @ApiModelProperty(value = "파일명", position = 4)
    private String name;

    @ApiModelProperty(value = "등록일자", position = 5)
    private String regDate;
}
