package com.exflyer.oddi.admin.api.external.youtube.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class YouTubeListResult {

  @ApiModelProperty(value = "유튜브ID", position = 1)
  private String youtubeId;

  @ApiModelProperty(value = "유튜브 플레이 ID", position = 2)
  private String youtubePlayId;

  @ApiModelProperty(value = "유튜브 제목", position = 3)
  private String youtubeTitle;

  @ApiModelProperty(value = "유튜브 설명", position = 4)
  private String youtubeDescription;

  @ApiModelProperty(value = "유튜브 썸네일", position = 5)
  private String youtubeThumbnails;

  @ApiModelProperty(value = "유튜브 URL", position = 6)
  private String youtubeUrl;

  @ApiModelProperty(value = "유튜브 등록일", position = 7)
  private String youtubeRegDate;

  @ApiModelProperty(value = "등록일", position = 8)
  private String regDate;

  @ApiModelProperty(value = "파트너순번", position = 9)
  private Long partnerSeq;

  @ApiModelProperty(value = "유튜브 재생ID", position = 10)
  private Boolean expo;
}
