package com.exflyer.oddi.admin.api.partner.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OddiZonePartnerListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "오디존 이름", position = 2)
  private String mallName;

  @ApiModelProperty(value = "파트너 이름", position = 3)
  @DecryptField
  private String ownerName;

  @ApiModelProperty(value = "전화번호", position = 4)
  @DecryptField
  private String ownerPhoneNumber;

  @ApiModelProperty(value = "운영 기기수", position = 5)
  private String deviceCnt;

  @ApiModelProperty(value = "가격", position = 6)
  private String slotPrice;

  @ApiModelProperty(value = "등록일시", position = 7)
  private String regDate;

  @ApiModelProperty(value = "최종 수정일", position = 8)
  private String modDate;

  @ApiModelProperty(value = "운영여부", position = 9)
  private String operationName;

  @ApiModelProperty(value = "지하철노선명", position = 10)
  private String subwayName;

  @ApiModelProperty(value = "지하철노선코드", position = 11)
  private String subwayCode;

}
