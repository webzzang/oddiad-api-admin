package com.exflyer.oddi.admin.api.notification.send.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class MemberNotificationReq {


  @ApiModelProperty(value = "발송 회원명", position = 1)
  private String memberName;

  @ApiModelProperty(value = "발송 회원 아이디", position = 2)
  private String memberId;


}
