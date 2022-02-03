package com.exflyer.oddi.admin.api.notification.history.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchHistoryResult {

  @ApiModelProperty(value = "발송이력 순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "발송타입명", position = 2)
  private String messageSendTypeName;

  @ApiModelProperty(value = "대상구분명", position = 3)
  private String targetTypeName;

  @ApiModelProperty(value = "고객명", position = 4)
  private String receiveName;

  @ApiModelProperty(value = "고객전화번호", position = 5)
  private String receivePhoneNumber;

  @ApiModelProperty(value = "발송매체", position = 6)
  private String sendTypeName;

  @ApiModelProperty(value = "결과", position = 7)
  private String successName;

  @ApiModelProperty(value = "발송자 이메일", position = 8)
  @DecryptField
  private String senderEmail;

  @ApiModelProperty(value = "발송일시", position = 9)
  private String regDate;

  @ApiModelProperty(value = "발송내용", position = 10)
  private String contents;

}
