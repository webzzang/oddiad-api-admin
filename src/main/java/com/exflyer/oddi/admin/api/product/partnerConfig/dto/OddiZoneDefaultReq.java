package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class OddiZoneDefaultReq {

    @ApiModelProperty(value = "종류(오디존, 지하철)", hidden = true)
    private String type;

    @ApiModelProperty(value = "기본 광고 파일명", position = 7)
    private String advName;

    @ApiModelProperty(value = "기본 광고 파일 순번", position = 20)
    private List<OddiZoneDefaultFileReq> defaultAdvFileList;

}
