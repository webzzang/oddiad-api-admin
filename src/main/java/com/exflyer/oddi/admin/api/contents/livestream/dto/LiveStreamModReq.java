package com.exflyer.oddi.admin.api.contents.livestream.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class LiveStreamModReq {

  @ApiModelProperty(value = "라이브스트리밍 채널 ID", position = 1)
  private String liveStreamChannelId;

  @ApiModelProperty(value = "라이브스트리밍 채널 운영일정 리스트", position = 2)
  private List<LiveStreamingReq> liveStreamList;

  @ApiModelProperty(value = "VOD 채널 리스트", position = 3)
  private List<LiveStreamingVodReq> vodList;

  @ApiModelProperty(value = "생성자 ID", hidden = true)
  private String regId;

}
