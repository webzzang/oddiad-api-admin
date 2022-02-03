package com.exflyer.oddi.admin.api.adv.make.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AdvMakeBasicInfoResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "광고처 이름", position = 2)
  private String mallName;

  @ApiModelProperty(value = "주소", position = 3)
  private String addr;

  @ApiModelProperty(value = "상세 주소", position = 4)
  private String detailAddr;

  @ApiModelProperty(value = "기기 이름", position = 5)
  private String deviceName;

  @ApiModelProperty(value = "기기 ID", position = 6)
  private String deviceId;

  @ApiModelProperty(value = "화면분할코드", position = 7)
  private String displayDiv;

  @ApiModelProperty(value = "화면분할코드명", position = 8)
  private String displayDivName;

  @ApiModelProperty(value = "측면(오른쪽)화면코드", position = 9)
  private String sideContentsType;

  @ApiModelProperty(value = "측면(오른쪽)화면코드명", position = 10)
  private String sideContentsTypeName;

  @ApiModelProperty(value = "등록일", position = 11)
  private String regDate;

  @ApiModelProperty(value = "현재날짜", position = 12)
  private LocalDateTime nowDate;

  @ApiModelProperty(value = "채널종류", position = 13)
  private String channelType;

  @ApiModelProperty(value = "디바이스 푸시 일자", position = 14)
  private String fcmRegDate;

  @ApiModelProperty(value = "성공여부", position = 15)
  private Boolean success;

  @ApiModelProperty(value = "성공여부명", position = 16)
  private String pushResult;


}
