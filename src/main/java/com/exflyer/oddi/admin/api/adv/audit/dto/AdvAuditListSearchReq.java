package com.exflyer.oddi.admin.api.adv.audit.dto;

import com.exflyer.oddi.admin.annotaions.EncryptField;
import com.exflyer.oddi.admin.share.dto.PagingSearch;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdvAuditListSearchReq extends PagingSearch {

  @ApiModelProperty(value = "광고채널", position = 1)
  private String channelType;

  @ApiModelProperty(value = "상태", position = 2)
  private String auditCode;

  @ApiModelProperty(value = "광고이름", position = 3)
  private String title;

  @ApiModelProperty(value = "고객이름", position = 4)
  private String name;

  @ApiModelProperty(value = "고객이메일", position = 5)
  @EncryptField
  private String email;

  @ApiModelProperty(value = "신청일 시작날짜(YYYYMMDD)", position = 6)
  private String searchRegStartDate;

  @ApiModelProperty(value = "신청일 종료날짜(YYYYMMDD)", position = 7)
  private String searchRegEndDate;

  @ApiModelProperty(value = "광고시작일 시작날짜(YYYYMMDD)", position = 8)
  private String searchStStartDate;

  @ApiModelProperty(value = "광고시작일 종료날짜(YYYYMMDD)", position = 8)
  private String searchStEndDate;

  @ApiModelProperty(value = "광고종료일 시작날짜(YYYYMMDD)", position = 9)
  private String searchEnStartDate;

  @ApiModelProperty(value = "광고종료일 종료날짜(YYYYMMDD)", position = 9)
  private String searchEnEndDate;

  @ApiModelProperty(value = "진행상태", position = 10)
  private String progressCode;

  @ApiModelProperty(value = "심사대기코드", hidden = true)
  private String auditWaitCode;

  @ApiModelProperty(value = "결제완료코드", hidden = true)
  private String paymentCompleteCode;

  @ApiModelProperty(value = "결제취소코드", hidden = true)
  private String paymentCancelCode;

}
