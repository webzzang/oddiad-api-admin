package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class OddiZoneConfigReq {

  @ApiModelProperty(value = "슬롯 카운트", position = 1)
  private Integer slotCount;

  @ApiModelProperty(value = "슬롯 영상 시간", position = 2)
  private Integer slotVideoTime;

  @ApiModelProperty(value = "디자인 요청 여부", position = 3)
  private Boolean designRequest;

  @ApiModelProperty(value = "화면 분할(1, 2, 3분할)", position = 4)
  private String displayDiv;

  @ApiModelProperty(value = "측면 화면 서비스 코드", position = 5)
  private String sideDisplayServiceCode;

  @ApiModelProperty(value = "하단 화면 서비스 코드", position = 6)
  private String bottomDisplayServiceCode;

  @ApiModelProperty(value = "기본 광고명", position = 7)
  private String advName;

  @ApiModelProperty(value = "종류(오디존, 지하철)", position = 9)
  private String type;

  @ApiModelProperty(value = "운영시작시간", position = 10)
  private String operationStartTime;

  @ApiModelProperty(value = "운영종료시간", position = 11)
  private String operationEndTime;

  @ApiModelProperty(value = "오디존 광고 시작 가능일(from)", position = 12)
  private Integer oddiAdvFromStartDate;

  @ApiModelProperty(value = "오디존 광고 시작 가능일(to)", position = 13)
  private Integer oddiAdvToStartDate;

  @ApiModelProperty(value = "오디존 최장 광고기간", position = 14)
  private Integer oddiAdvMaxDate;

  @ApiModelProperty(value = "오디존 광고 신청취소 가능일", position = 15)
  private Integer oddiAdvCancelDate;

  @ApiModelProperty(value = "지하철 익월 광고 마감일", position = 16)
  private Integer subwayAdvLastDate;

  @ApiModelProperty(value = "지하철 최대광고 시작일", position = 17)
  private Integer subwayAdvMaxStartDate;

  @ApiModelProperty(value = "지하철 광고 신청취소 가능일", position = 18)
  private Integer subwayAdvCancelDate;

  @ApiModelProperty(value = "지하철 최장 광고기간", position = 19)
  private Integer subwayAdvMaxDate;

  @ApiModelProperty(value = "기본 광고 파일 순번", position = 20)
  private List<OddiZoneDefaultFileReq> defaultAdvFileList;

}
