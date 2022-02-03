package com.exflyer.oddi.admin.api.adv.make.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvMakeFilesResult {

  @ApiModelProperty(value = "광고순번", position = 1)
  private Long advSeq;

  @ApiModelProperty(value = "파일순번", position = 3)
  private String fileSeq;

  @ApiModelProperty(value = "파일패스", position = 4)
  private String filePath;

  @ApiModelProperty(value = "파일명", position = 5)
  private String fileName;

  @ApiModelProperty(value = "파일타입", position = 6)
  private String type;

  @ApiModelProperty(value = "노출순서", position = 7)
  private Integer viewOrder;
}
