package com.exflyer.oddi.admin.api.sales.adv.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchAdvHistoryReq extends PagingSearch {

  @ApiModelProperty(value = "채널타입", position = 1)
  private String channelType;

  @ApiModelProperty(value = "광고처", position = 2)
  private String mallName;

  @ApiModelProperty(value = "광고기기", position = 3)
  private String deviceName;

  @ApiModelProperty(value = "광고이름", position = 4)
  private String advName;

  @EncryptField
  @ApiModelProperty(value = "고객 이메일", position = 5)
  private String email;

  @ApiModelProperty(value = "광고주", position = 6)
  private String ownerName;

  @ApiModelProperty(value = "광고시작일 시작일자(YYYYMMDD)", position = 7)
  private String searchAdvStStartDate;

  @ApiModelProperty(value = "광고시작일 종료일자(YYYYMMDD)", position = 8)
  private String searchAdvStEndDate;

  @ApiModelProperty(value = "광고종료일 시작일자(YYYYMMDD)", position = 9)
  private String searchAdvEnStartDate;

  @ApiModelProperty(value = "광고종료일 종료일자(YYYYMMDD)", position = 10)
  private String searchAdvEnEndDate;

}
