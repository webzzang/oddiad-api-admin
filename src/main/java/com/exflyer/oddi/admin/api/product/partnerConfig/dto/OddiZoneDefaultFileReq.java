package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class OddiZoneDefaultFileReq {

  @ApiModelProperty(value = "기본 광고 파일 순번", position = 1)
  private Long defaultAdvFileSeq;

  @ApiModelProperty(value = "기본 광고 파일 종류", position = 2)
  private String defaultAdvType;

}
