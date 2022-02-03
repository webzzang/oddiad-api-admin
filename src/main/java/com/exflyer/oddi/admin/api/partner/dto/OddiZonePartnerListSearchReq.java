package com.exflyer.oddi.admin.api.partner.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OddiZonePartnerListSearchReq extends PagingSearch {


  @ApiModelProperty(value = "채널타입", position = 1)
  private String channelType;

  @ApiModelProperty(value = "오디존 이름", position = 2)
  private String mallName;

  @ApiModelProperty(value = "파트너 이름", position = 3)
  private String ownerName;

  @ApiModelProperty(value = "전화번호", position = 4)
  @EncryptField
  private String ownerPhoneNumber;

  @ApiModelProperty(value = "등록일 검색 시작 날짜(YYYYMMDD)", position = 5)
  private String searchRegStartDate;

  @ApiModelProperty(value = "등록일 검색 종료 날짜(YYYYMMDD)", position = 6)
  private String searchRegEndDate;

  @ApiModelProperty(value = "운영여부", position = 7)
  private Boolean operation;

  @ApiModelProperty(value = "지하철노선코드", position = 8)
  private String subwayCode;

}
