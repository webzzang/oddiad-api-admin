package com.exflyer.oddi.admin.api.adv.make.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvMakeExpoReq {

  @ApiModelProperty(value = "광고순번", position = 1)
  private Long advSeq;

  /*@ApiModelProperty(value = "방송순번", position = 2)
  private Long broadcastingSeq;*/

  @ApiModelProperty(value = "노출여부", position = 3)
  private Boolean expo;

  @ApiModelProperty(value = "노출순서", position = 4)
  private Integer viewOrder;

}
