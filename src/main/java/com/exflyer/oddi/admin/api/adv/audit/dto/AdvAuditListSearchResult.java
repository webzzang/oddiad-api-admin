package com.exflyer.oddi.admin.api.adv.audit.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdvAuditListSearchResult {

  @ApiModelProperty(value = "순번", position = 1)
  private Long seq;

  @ApiModelProperty(value = "고객 ID", position = 2)
  @DecryptField
  private String id;

  @ApiModelProperty(value = "고객 이름", position = 3)
  private String name;

  @ApiModelProperty(value = "광고 이름", position = 4)
  private String title;

  @ApiModelProperty(value = "광고 채널 코드", position = 5)
  private String channelType;

  @ApiModelProperty(value = "광고 채널 명", position = 6)
  private String channelTypeName;

  @ApiModelProperty(value = "디자인 타입", position = 7)
  private String type;

  @ApiModelProperty(value = "디자인 타입명", position = 8)
  private String designTypeName;

  @ApiModelProperty(value = "광고시작일", position = 9)
  private String startDate;

  @ApiModelProperty(value = "광고종료일", position = 10)
  private String endDate;

  @ApiModelProperty(value = "신청일시", position = 11)
  private String regDate;

  @ApiModelProperty(value = "상태 코드", position = 12)
  private String auditCode;

  @ApiModelProperty(value = "상태 코드명", position = 13)
  private String auditCodeName;

  @ApiModelProperty(value = "진행 코드", position = 12)
  private String progressCode;

  @ApiModelProperty(value = "진행 코드명", position = 13)
  private String progressCodeName;

  @ApiModelProperty(value = "이메일", position = 14)
  @DecryptField
  private String email;


}
