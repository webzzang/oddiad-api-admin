package com.exflyer.oddi.admin.api.partner.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.models.PartnerSubway;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import software.amazon.ion.Decimal;

@Data
public class OddiZoneAddReq {

  @ApiModelProperty(value = "채널타입", position = 1)
  private String channelType;

  @ApiModelProperty(value = "오디존 이름", position = 2)
  private String mallName;

  @ApiModelProperty(value = "소유자 이름", position = 3)
  private String ownerName;

  @ApiModelProperty(value = "소유자 전화번호", position = 4)
  @EncryptField
  private String ownerPhoneNumber;

  @ApiModelProperty(value = "우편번호", position = 5)
  private String zipcode;

  @ApiModelProperty(value = "주소", position = 6)
  private String addr;

  @ApiModelProperty(value = "지번 주소", position = 7)
  private String oldAddr;

  @ApiModelProperty(value = "상세 주소", position = 8)
  private String detailAddr;

  @ApiModelProperty(value = "요약", position = 9)
  private String summary;

  @ApiModelProperty(value = "설명", position = 10)
  private String description;

  @ApiModelProperty(value = "소개 이미지 SEQ", position = 11)
  private List<Long> fileSeq;

  @ApiModelProperty(value = "태그", position = 12)
  private List<String> tags;

  @ApiModelProperty(value = "배지 코드", position = 13)
  private String badgeCode;

  @ApiModelProperty(value = "광고 사례 노출 여부", position = 14)
  private Boolean advCaseExpo = true;

  @ApiModelProperty(value = "운영 여부", position = 15)
  private Boolean operation = true;

  @ApiModelProperty(value = "메모", position = 16)
  private String memo;

  @ApiModelProperty(hidden = true)
  private String regId;

  @ApiModelProperty(hidden = true)
  private Long seq;

  @ApiModelProperty(hidden = true)
  private String modId;

  @PositiveOrZero
  @NotNull
  @ApiModelProperty(value = "총 슬롯 카운트", position = 17)
  private Integer totalSlot;

  @PositiveOrZero
  @NotNull
  @ApiModelProperty(value = "슬롯 당 영상 시간", position = 18)
  private Integer slotVideoTime;

  @ApiModelProperty(value = "슬롯 당 가격", position = 19)
  @PositiveOrZero
  @NotNull
  private Integer slotPrice;

  @ApiModelProperty(value = "운영일", position = 20)
  private String operationWeek;

  @ApiModelProperty(value = "운영 시작 날짜", position = 21)
  private String operationStartTime;

  @ApiModelProperty(value = "운영 종료 날짜", position = 22)
  private String operationEndTime;

  @PositiveOrZero
  @NotNull
  @ApiModelProperty(value = "일 노출 카운트", position = 23)
  private Integer dayExpoCount;

  @ApiModelProperty(value = "기기", position = 24)
  private List<PartnerDeviceAddReq> deviceId;

  /*@ApiModelProperty(value = "위도", position = 25)
  private Double latitude;

  @ApiModelProperty(value = "경도", position = 26)
  private Double longitude;

  @ApiModelProperty(value = "X좌표", position = 27)
  private Double gridX;

  @ApiModelProperty(value = "Y좌표", position = 28)
  private Double gridY;*/

  @ApiModelProperty(value = "주소_시, 군", position = 29)
  private String addrSi;

  @ApiModelProperty(value = "주소_구", position = 30)
  private String addrGu;

  @ApiModelProperty(value = "주소_동,읍,리", position = 31)
  private String addrDong;

  @ApiModelProperty(value = "지하철 노선", position = 32)
  private List<PartnerSubway> subwayList;

  @ApiModelProperty(value = "위치(지하철 광고 위치)", position = 25)
  private String location;

  @ApiModelProperty(value = "화면(지하철 광고 화면)", position = 26)
  private String display;

  @ApiModelProperty(value = "화면(지하철 광고 화면)", position = 27)
  private Boolean operationEndTomorrowGbn;

  @ApiModelProperty(value = "도면 파일순번", position = 28)
  private Long mapFileSeq;
}
