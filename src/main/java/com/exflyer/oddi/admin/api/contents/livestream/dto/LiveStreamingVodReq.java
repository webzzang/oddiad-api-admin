package com.exflyer.oddi.admin.api.contents.livestream.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LiveStreamingVodReq {

  @ApiModelProperty(value = "유튜브 ID", position = 1)
  private String youtubeId;

  @ApiModelProperty(value = "광고처 순번", position = 2)
  private Long partnerSeq;

  @ApiModelProperty(value = "노출여부", position = 3)
  private Boolean expo;

}
