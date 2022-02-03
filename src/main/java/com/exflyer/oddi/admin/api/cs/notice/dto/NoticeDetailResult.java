package com.exflyer.oddi.admin.api.cs.notice.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NoticeDetailResult {

  @ApiModelProperty(value = "순번", position = 1)
  private String seq;

  @ApiModelProperty(value = "전시여부", position = 2)
  private Boolean expo = false;

  @ApiModelProperty(value = "최상단 고정여부", position = 3)
  private Boolean topLocation = false;

  @ApiModelProperty(value = "제목", position = 4)
  private String title;

  @ApiModelProperty(value = "이미지순번", position = 5)
  private Long fileSeq;

  @ApiModelProperty(value = "파일명", position = 6)
  private String fileName;

  @ApiModelProperty(value = "파일패스", position = 7)
  private String filePath;

  @ApiModelProperty(value = "내용", position = 8)
  private String contents;

  @ApiModelProperty(value = "등록자ID", position = 9)
  @DecryptField
  private String regId;

  @ApiModelProperty(value = "등록일", position = 10)
  private String regDate;

  @ApiModelProperty(value = "수정자ID", position = 11)
  @DecryptField
  private String modId;

  @ApiModelProperty(value = "수정일", position = 12)
  private String modDate;

}
