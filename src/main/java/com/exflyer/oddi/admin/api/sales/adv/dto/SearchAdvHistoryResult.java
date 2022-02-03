package com.exflyer.oddi.admin.api.sales.adv.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchAdvHistoryResult {

  @ApiModelProperty(value = "채널타입", position = 1)
  private String channelType;

  @ApiModelProperty(value = "채널타입명", position = 2)
  private String channelTypeName;

  @ApiModelProperty(value = "광고처", position = 3)
  private String mallName;

  @ApiModelProperty(value = "광고기기", position = 4)
  private String deviceName;

  @ApiModelProperty(value = "광고이름", position = 5)
  private String advName;

  @DecryptField
  @ApiModelProperty(value = "고객 이메일", position = 6)
  private String email;

  @ApiModelProperty(value = "광고주", position = 7)
  private String ownerName;

  @ApiModelProperty(value = "광고 시작일", position = 8)
  private String advStartDate;

  @ApiModelProperty(value = "광고 종료일", position = 9)
  private String advEndDate;

}
