package com.exflyer.oddi.admin.api.contents.livestream.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class LiveStreamingReq {

  @ApiModelProperty(value = "라이브스트리밍 제목", position = 1)
  private String title;

  @ApiModelProperty(value = "운영일", position = 2)
  private String operationDay;

  @ApiModelProperty(value = "시작시간(HHmmss 6자리)", position = 3)
  private String startTime;

  @ApiModelProperty(value = "종료시간(HHmmss 6자리)", position = 4)
  private String endTime;

  @ApiModelProperty(value = "생성 ID", hidden = true)
  private String regId;

  @ApiModelProperty(value = "변경 ID", hidden = true)
  private String modId;

}
