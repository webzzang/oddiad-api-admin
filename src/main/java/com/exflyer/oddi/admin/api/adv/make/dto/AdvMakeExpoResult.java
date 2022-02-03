package com.exflyer.oddi.admin.api.adv.make.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvMakeExpoResult {

  @ApiModelProperty(value = "광고순번", position = 1)
  private Long advSeq;

  @ApiModelProperty(value = "광고 이름", position = 2)
  private String title;

  @ApiModelProperty(value = "시작일자", position = 3)
  private String startDate;

  @ApiModelProperty(value = "종료일자", position = 4)
  private String endDate;

  @ApiModelProperty(value = "노출여부", position = 5)
  private Boolean expo = false;

  @ApiModelProperty(value = "노출순서", position = 6)
  private Integer viewOrder;

  /*@ApiModelProperty(value = "방송순번", position = 7)
  private Long broadcastingSeq;*/

}
