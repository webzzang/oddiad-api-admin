package com.exflyer.oddi.admin.api.notification.send.dto;

import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchGroupTargetResult extends PagingSearch {

  @ApiModelProperty(value = "발송 그룹명", position = 1)
  private String groupName;

  @ApiModelProperty(value = "발송 그룹대상명(파트너명, 회원명, 전화번호)", position = 2)
  private String targetName;

  @ApiModelProperty(value = "발송 그룹대상 식별 코드(partnerSeq, memberId)", position = 3)
  private String targetSeq;

  @ApiModelProperty(value = "발송 그룹대상 코드", position = 4)
  private String targetCode;

  @ApiModelProperty(value = "발송 그룹대상 코드명", position = 5)
  private String targetCodeName;

}
