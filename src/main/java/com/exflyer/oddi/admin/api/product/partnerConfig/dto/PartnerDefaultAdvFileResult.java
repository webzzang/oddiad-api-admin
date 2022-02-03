package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PartnerDefaultAdvFileResult {

  @ApiModelProperty(value = "기본 광고 파일 순번", position = 1)
  private Long defaultAdvFileSeq;

  @ApiModelProperty(value = "기본광고 파일타입", position = 2)
  private String defaultAdvType;

  @ApiModelProperty(value = "파일경로", position = 3)
  private String filePath;

  @ApiModelProperty(value = "파일명", position = 4)
  private String fileName;

}
