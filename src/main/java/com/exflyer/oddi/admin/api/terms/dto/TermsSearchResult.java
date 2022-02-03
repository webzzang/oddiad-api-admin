package com.exflyer.oddi.admin.api.terms.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TermsSearchResult {

  @ApiModelProperty(value = "순번")
  private Long seq;

  @ApiModelProperty(value = "버젼", position = 1)
  private String version;

  @ApiModelProperty(value = "제목", position = 2)
  private String title;

  @ApiModelProperty(value = "등록자 이메일", position = 3)
  @DecryptField
  private String regEmail;

  @ApiModelProperty(value = "등록 날짜", position = 4)
  private String regDate;

  @ApiModelProperty(value = "수정자 이메일", position = 5)
  @DecryptField
  private String modEmail;

  @ApiModelProperty(value = "수정 날짜", position = 6)
  private String modDate;

  @ApiModelProperty(value = "상태 코드 이름", position = 7)
  private String statusCodeName;

  @ApiModelProperty(value = "필수여부", position = 8)
  private Boolean required;

  @ApiModelProperty(value = "광고약관여부", position = 9)
  private Boolean advTerms;

}
