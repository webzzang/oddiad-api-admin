package com.exflyer.oddi.admin.api.cs.faq.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FaqRegReq {

  @NotBlank
  @ApiModelProperty(value = "카테고리 코드", position = 1)
  private String categoryCode;

  @NotBlank
  @ApiModelProperty(value = "제목", position = 2)
  private String title;

  @NotBlank
  @ApiModelProperty(value = "내용", position = 3)
  private String contents;

  @ApiModelProperty(value = "노출여부", position = 4)
  private Boolean expo = false;

  @ApiModelProperty(hidden = true)
  private String regId;

  @ApiModelProperty(hidden = true)
  private LocalDateTime regDate;

}
