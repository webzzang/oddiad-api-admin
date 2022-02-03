package com.exflyer.oddi.admin.api.contents.banner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BannerRegReq {

  @ApiModelProperty(value = "배너명", position = 1)
  private String name;

  @ApiModelProperty(value = "파일 순번", position = 2)
  private Long fileSeq;

  @ApiModelProperty(value = "사용 여부", position = 3)
  private Boolean usable;

  @ApiModelProperty(value = "노출 시작 날짜", position = 4)
  private String expoStartDate;

  @ApiModelProperty(value = "노출 종료 날짜", position = 5)
  private String expoEndDate;

  @ApiModelProperty(value = "메모", position = 6)
  private String memo;

  @ApiModelProperty(value = "노출 텍스트", position = 7)
  private String description;

  @ApiModelProperty(value = "링크 URL", position = 8)
  private String routerLink;

  @ApiModelProperty(value = "버튼이름", position = 9)
  private String buttonName;

  @ApiModelProperty(value = "순번", hidden = true)
  private Long seq;

  @ApiModelProperty(value = "생성 id", hidden = true)
  private String regId;

  @ApiModelProperty(value = "변경 id", hidden = true)
  private String modId;

  @ApiModelProperty(value = "종류(web, device)", hidden = true)
  private String type;

  @ApiModelProperty(value = "위치 코드(우측, 상단, 하단, 팝업)", hidden = true)
  private String locationCode;

}
