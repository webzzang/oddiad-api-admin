package com.exflyer.oddi.admin.api.contents.livestream.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class LiveScheduleModReq {

  @ApiModelProperty(value = "파트너 순번", position = 1, required = true)
  private Long partnerSeq;

  @ApiModelProperty(value = "노출여부", position = 2, required = true)
  private Boolean expo;

  @ApiModelProperty(value = "유튜브 ID", hidden = true)
  private String youtubeId;

}
