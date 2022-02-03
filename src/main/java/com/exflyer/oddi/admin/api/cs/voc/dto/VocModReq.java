package com.exflyer.oddi.admin.api.cs.voc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VocModReq {

  @ApiModelProperty(hidden = true)
  private Long seq;

  @ApiModelProperty(value = "답변", position = 1)
  private String answer;

  @ApiModelProperty(hidden = true)
  private String answerRegId;

  @ApiModelProperty(value = "고객 이름", position = 2)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 3)
  private String phoneNumber;

  @ApiModelProperty(value = "제목", position = 4)
  private String title;

  @ApiModelProperty(value = "문의글URL", position = 5)
  private String url;

}
