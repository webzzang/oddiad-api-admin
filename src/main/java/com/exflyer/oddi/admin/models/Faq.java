package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.cs.faq.dto.FaqModReq;
import com.exflyer.oddi.admin.api.cs.faq.dto.FaqRegReq;
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
 * FAQ
 */
@Data
@Entity
@ApiModel("FAQ")
@Table(name = "faq")
@NoArgsConstructor
public class Faq implements Serializable {

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
   * 카테고리 코드
   */
  @ApiModelProperty("카테고리 코드")
  @Column(name = "category_code", nullable = false)
  private String categoryCode;

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
   * 노출 여부
   */
  @ApiModelProperty("노출 여부")
  @Column(name = "expo", nullable = false)
  private Boolean expo = false;

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
  @Column(name = "mod_date")
  @ApiModelProperty("변경 날짜")
  private LocalDateTime modDate;

  /**
   * 변경 id
   */
  @Column(name = "mod_id")
  @ApiModelProperty("변경 id")
  private String modId;

  public void setModifyReq(FaqModReq faqModReq) {
    this.categoryCode = faqModReq.getCategoryCode();
    this.title = faqModReq.getTitle();
    this.contents = faqModReq.getContents();
    this.expo = faqModReq.getExpo();
    this.modId = faqModReq.getModId();
    this.modDate = LocalDateUtils.krNow();
  }

  public Faq(FaqRegReq faqRegReq){
      this.categoryCode = faqRegReq.getCategoryCode();
      this.title = faqRegReq.getTitle();
      this.contents = faqRegReq.getContents();
      this.expo = faqRegReq.getExpo();
      this.regId = faqRegReq.getRegId();
      this.regDate = LocalDateUtils.krNow();
  }

}
