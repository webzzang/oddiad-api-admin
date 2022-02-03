package com.exflyer.oddi.admin.api.terms.dto;

import com.exflyer.oddi.admin.annotaions.DecryptField;
import com.exflyer.oddi.admin.api.terms.codes.TermsStatusCodes;
import com.exflyer.oddi.admin.api.terms.codes.TermsTypeCodes;
import com.exflyer.oddi.admin.models.Terms;
import io.swagger.annotations.ApiModelProperty;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class TermsDetailResult {

  @ApiModelProperty(hidden = true)
  private Long seq;

  @ApiModelProperty(value = "상태코드(TST001 : 임시저장\n"
    + "TST002 : 사용\n"
    + "TST003 : 폐기\n)", position = 2)
  private String statusCode;

  @ApiModelProperty(value = "상태코드 이름", position = 2)
  private String statusCodeName;

  @ApiModelProperty(value = "버젼", position = 1)
  @NotBlank
  private String version;

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

  @ApiModelProperty(value = "메모", position = 6)
  private String typeName;


  @ApiModelProperty(value = "생성 ID", position = 6)
  @DecryptField
  private String regId;

  @ApiModelProperty(value = "생성 날짜", position = 6)
  private String regDate;

  @ApiModelProperty(value = "수정 ID", position = 6)
  @DecryptField
  private String modId;

  @ApiModelProperty(value = "수정 날짜", position = 6)
  private String modDate;

  @ApiModelProperty(value = "필수여부", position = 7)
  private Boolean required;

  public TermsDetailResult(Terms terms) {
    this.seq = terms.getSeq();
    this.title = terms.getTitle();
    this.contents = terms.getContents();
    this.version = terms.getVersion();
    this.memo = terms.getMemo();
    this.advTerms = terms.getAdvTerms();
    this.typeName = TermsTypeCodes.getCodeNameByCode(terms.getType());
    this.statusCode = terms.getStatusCode();
    this.statusCodeName = TermsStatusCodes.getCodeNameByCode(terms.getStatusCode());
    this.regId = terms.getRegId();
    this.required = terms.getRequired();
    this.regDate = terms.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    this.modId = StringUtils.defaultString(terms.getModId(), "");
    if (terms.getModDate() == null) {
      this.modDate = "";
    }else{
      this.modDate = terms.getModDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

  }
}
