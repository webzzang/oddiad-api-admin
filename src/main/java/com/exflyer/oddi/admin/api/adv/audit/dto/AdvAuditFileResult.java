package com.exflyer.oddi.admin.api.adv.audit.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvAuditFileResult {

    @ApiModelProperty(value = "광고순번", position = 1)
    private String advSeq;

    @ApiModelProperty(value = "디자인타입코드", position = 2)
    private String type;

    @ApiModelProperty(value = "디자인타입코드명", position = 3)
    private String designTypeName;

    @ApiModelProperty(value = "파일순번", position = 4)
    private String fileSeq;

    @ApiModelProperty(value = "파일패스", position = 5)
    private String path;

    @ApiModelProperty(value = "파일명", position = 6)
    private String name;

    @ApiModelProperty(value = "등록일자", position = 7)
    private String regDate;
}
