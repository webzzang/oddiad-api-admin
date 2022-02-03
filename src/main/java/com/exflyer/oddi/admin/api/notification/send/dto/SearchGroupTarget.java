package com.exflyer.oddi.admin.api.notification.send.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchGroupTarget extends PagingSearch {

  @ApiModelProperty(value = "발송 그룹명", position = 1)
  private String groupName;

  @ApiModelProperty(value = "발송 그룹대상", position = 2)
  private String targetName;

  @ApiModelProperty(value = "발송 그룹대상 코드", position = 3)
  private String targetCode;

  @ApiModelProperty(value = "오디 파트너 코드값(hidden)", hidden = true)
  private String oddiPartnerCode;

  @ApiModelProperty(value = "지하철 파트너 코드값(hidden)", hidden = true)
  private String subwayPartnerCode;

  @ApiModelProperty(value = "회원 코드값(hidden)", hidden = true)
  private String notificationMemberCode;

  @ApiModelProperty(value = "전화번호 코드값(hidden)", hidden = true)
  private String notificationTelCode;

}
