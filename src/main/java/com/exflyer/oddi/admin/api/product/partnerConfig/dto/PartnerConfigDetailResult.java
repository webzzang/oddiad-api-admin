package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PartnerConfigDetailResult {

  @ApiModelProperty(value = "슬롯 카운트", position = 1)
  private Integer slotCount;

  @ApiModelProperty(value = "슬롯 영상 시간", position = 1)
  private Integer slotVideoTime;

  @ApiModelProperty(value = "디자인 요청 여부", position = 1)
  private Boolean designRequest;

  @ApiModelProperty(value = "분할 화면 분할(1, 2, 3분할)", position = 1)
  private String displayDiv;

  @ApiModelProperty(value = "측면 화면 서비스 코드", position = 1)
  private String sideDisplayServiceCode;

  @ApiModelProperty(value = "하단 화면 서비스 코드", position = 1)
  private String bottomDisplayServiceCode;

  @ApiModelProperty(value = "하단 화면 서비스 코드명", position = 1)
  private String bottomDisplayServiceName;

  @ApiModelProperty(value = "기본 광고 이름", position = 1)
  private String advName;

  @ApiModelProperty(value = "종류(오디존, 지하철)", position = 1)
  private String type;

  @ApiModelProperty(value = "오디존 광고 시작 가능일(from)", position = 1)
  private Integer oddiAdvFromStartDate;

  @ApiModelProperty(value = "오디존 광고 시작 가능일(to)", position = 1)
  private Integer oddiAdvToStartDate;

  @ApiModelProperty(value = "오디존 최장 광고기간", position = 1)
  private Integer oddiAdvMaxDate;

  @ApiModelProperty(value = "오디존 광고 신청취소 가능일", position = 1)
  private Integer oddiAdvCancelDate;

}
