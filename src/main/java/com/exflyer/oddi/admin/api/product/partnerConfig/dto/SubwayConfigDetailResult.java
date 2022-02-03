package com.exflyer.oddi.admin.api.product.partnerConfig.dto;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class SubwayConfigDetailResult {

  @ApiModelProperty(value = "슬롯 카운트", position = 1)
  private Integer slotCount;

  @ApiModelProperty(value = "슬롯 영상 시간", position = 2)
  private Integer slotVideoTime;

  @ApiModelProperty(value = "디자인 요청 여부", position = 3)
  private Boolean designRequest;

  @ApiModelProperty(value = "종류(오디존, 지하철)", position = 4)
  private String channelType;

  @ApiModelProperty(value = "운영시작시간", position = 5)
  private String operationStartTime;

  @ApiModelProperty(value = "운영종료시간", position = 6)
  private String operationEndTime;

  @ApiModelProperty(value = "지하철 익월 광고 마감일", position = 1)
  private Integer subwayAdvLastDate;

  @ApiModelProperty(value = "지하철 최대광고 시작일", position = 1)
  private Integer subwayAdvMaxStartDate;

  @ApiModelProperty(value = "지하철 광고 신청취소 가능일", position = 1)
  private Integer subwayAdvCancelDate;

  @ApiModelProperty(value = "지하철 최장 광고기간", position = 1)
  private Integer subwayAdvMaxDate;

}
