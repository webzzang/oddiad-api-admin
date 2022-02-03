package com.exflyer.oddi.admin.api.terms.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TermsAddReq {

  @ApiModelProperty(value = "버젼", position = 1)
  @NotBlank
  private String version;

  @ApiModelProperty(value = "상태코드(TST001 : 임시저장\n"
    + "TST002 : 사용\n"
    + "TST003 : 폐기\n)", position = 2)
  private String statusCode;

  @ApiModelProperty(value = "이름(제목)", position = 3)
  @NotBlank
  private String title;

  @ApiModelProperty(value = "내용", position = 4)
  @NotBlank
  private String contents;

  @ApiModelProperty(value = "광고신청시 약관 여부", position = 5)
  private Boolean advTerms = false;

  @ApiModelProperty(value = "메모", position = 6)
  private String memo;

  @ApiModelProperty(value = "필수여부", position = 7)
  private Boolean required = false;

  @ApiModelProperty(hidden = true)
  private String regId;

  @ApiModelProperty(hidden = true)
  private String typeCode;



}
