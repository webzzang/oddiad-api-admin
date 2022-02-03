package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.terms.dto.TermsAddReq;
import com.exflyer.oddi.admin.api.terms.dto.TermsModReq;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 약관
 */
@Data
@Entity
@ApiModel("약관")
@Table(name = "terms")
@NoArgsConstructor
public class Terms implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @ApiModelProperty("순번")
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 제목
   */
  @ApiModelProperty("제목")
  @Column(name = "title", nullable = false)
  private String title;

  /**
   * 내용
   */
  @ApiModelProperty("내용")
  @Column(name = "contents", nullable = false)
  private String contents;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 생성 id
   */
  @ApiModelProperty("생성 id")
  @Column(name = "reg_id", nullable = false)
  private String regId;

  /**
   * 변경 날짜
   */
  @ApiModelProperty("변경 날짜")
  @Column(name = "mod_date", nullable = false)
  private LocalDateTime modDate;

  /**
   * 변경 id
   */
  @ApiModelProperty("변경 id")
  @Column(name = "mod_id", nullable = false)
  private String modId;

  /**
   * 버젼
   */
  @ApiModelProperty("버젼")
  @Column(name = "version")
  private String version;

  /**
   * 메모
   */
  @Column(name = "memo")
  @ApiModelProperty("메모")
  private String memo;

  /**
   * 종류
   */
  @ApiModelProperty("종류")
  @Column(name = "type", nullable = false)
  private String type;

  /**
   * 상태 코드
   */
  @ApiModelProperty("상태 코드")
  @Column(name = "status_code", nullable = false)
  private String statusCode;

  @ApiModelProperty("광고신청 약관 여부")
  @Column(name = "adv_terms", nullable = false)
  private Boolean advTerms;

  @ApiModelProperty("필수여부")
  @Column(name = "required")
  private Boolean required;

  public Terms(TermsAddReq termsAddReq) {
    this.title = termsAddReq.getTitle();
    this.contents = termsAddReq.getContents();
    this.type = termsAddReq.getTypeCode();
    this.version = termsAddReq.getVersion();
    this.memo = termsAddReq.getMemo();
    this.statusCode = termsAddReq.getStatusCode();
    this.advTerms = termsAddReq.getAdvTerms();
    this.regId = termsAddReq.getRegId();
    this.required = termsAddReq.getRequired();
    this.regDate = LocalDateUtils.krNow();
  }

  public void setInfoByTermsModReq(TermsModReq termsModReq) {
    this.title = termsModReq.getTitle();
    this.contents = termsModReq.getContents();
    this.version = termsModReq.getVersion();
    this.memo = termsModReq.getMemo();
    this.statusCode = termsModReq.getStatusCode();
    this.advTerms = termsModReq.getAdvTerms();
    this.required = termsModReq.getRequired();
    this.modId = termsModReq.getModId();
    this.modDate = LocalDateUtils.krNow();
  }
}
