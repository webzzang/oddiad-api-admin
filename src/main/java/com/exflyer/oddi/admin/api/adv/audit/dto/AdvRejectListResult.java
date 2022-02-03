package com.exflyer.oddi.admin.api.adv.audit.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvRejectListResult {


  @ApiModelProperty(value = "광고 신청일시", position = 1)
  private String advRegDate;

  @ApiModelProperty(value = "광고 거절코드명", position = 2)
  private String rejectionCodeName;

  @ApiModelProperty(value = "광고 거절이유", position = 3)
  private String rejectionReason;

  @ApiModelProperty(value = "생성(거절) 명", position = 4)
  private String rejectionName;

  @ApiModelProperty(value = "생성(거절) 날짜", position = 5)
  private String rejectionDate;


}
