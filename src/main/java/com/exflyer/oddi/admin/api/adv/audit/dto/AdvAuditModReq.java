package com.exflyer.oddi.admin.api.adv.audit.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import com.exflyer.oddi.admin.models.AdvFile;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class AdvAuditModReq {

  @ApiModelProperty(hidden = true)
  private Long seq;

  @ApiModelProperty(value = "파일타입", hidden = true)
  private String type;

  @ApiModelProperty(hidden = true)
  private Long fileSeq;

  /*@ApiModelProperty(value = "진행 코드", hidden = true)
  private String progressCode;*/

  @ApiModelProperty(value = "심사 코드", hidden = true)
  private String auditCode;

  @ApiModelProperty(value = "심사 ID", hidden = true)
  private String auditId;

  @ApiModelProperty(value = "심사/보류 구분(심사:1,보류:2)", position = 1)
  private Integer auditEnum;

  @ApiModelProperty(value = "보류타입 코드", position = 2)
  private String rejectionCode;

  @ApiModelProperty(value = "보류 이유", position = 3)
  private String rejectionReason;

  @ApiModelProperty(value = "메모", position = 4)
  private String memo;

  @ApiModelProperty(value = "새로운 파일리스트(adv_file)", position = 5)
  private List<AdvFile> advFileList;

  @ApiModelProperty(value = "고객 이름", position = 6)
  private String name;

  @ApiModelProperty(value = "전화번호", position = 7)
  private String phoneNumber;

  @ApiModelProperty(value = "광고이름", position = 8)
  private String title;

  @ApiModelProperty(value = "광고 신청일시", position = 9)
  private String regDate;

  @ApiModelProperty(value = "광고 시작일", position = 10)
  private String startDate;

  @ApiModelProperty(value = "광고 종료일", position = 11)
  private String endDate;

  @ApiModelProperty(value = "회원 ID", position = 12)
  private String memberId;

}
