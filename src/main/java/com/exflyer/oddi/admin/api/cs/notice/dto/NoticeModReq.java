package com.exflyer.oddi.admin.api.cs.notice.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoticeModReq {

  @ApiModelProperty(hidden = true)
  private Long seq;

  @NotBlank
  @ApiModelProperty(value = "제목", position = 1)
  private String title;

  @NotBlank
  @ApiModelProperty(value = "내용", position = 2)
  private String contents;

  @ApiModelProperty(value = "전시여부", position = 3)
  private Boolean expo = false;

  @ApiModelProperty(value = "최상단고정여부", position = 4)
  private Boolean topLocation = false;

  @ApiModelProperty(hidden = true)
  private Long fileSeq;

  @ApiModelProperty(hidden = true)
  private String modId;

}
