package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.product.bundle.dto.ProductBundleReq;
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
 * 묶음상품관리 정보
 */
@Data
@Entity
@Table(name = "product")
@ApiModel("묶음상품관리 정보")
@NoArgsConstructor
public class Product implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @ApiModelProperty("seq")
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 상품이름
   */
  @ApiModelProperty("name")
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * 금액
   */
  @ApiModelProperty("price")
  @Column(name = "price", nullable = false)
  private Integer price;

  /**
   * 설명
   */
  @ApiModelProperty("description")
  @Column(name = "description", nullable = false)
  private String description;

  /**
   * 운영여부
   */
  @ApiModelProperty("operation")
  @Column(name = "operation", nullable = false)
  private Boolean operation;

  /**
   * 메모
   */
  @ApiModelProperty("memo")
  @Column(name = "memo")
  private String memo;

  /**
   * 생성날짜
   */
  @ApiModelProperty("reg_date")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 생성ID
   */
  @ApiModelProperty("reg_id")
  @Column(name = "reg_id", nullable = false)
  private String regId;

  /**
   * 변경날짜
   */
  @ApiModelProperty("mod_date")
  @Column(name = "mod_date")
  private LocalDateTime modDate;

  /**
   * 변경ID
   */
  @ApiModelProperty("mod_id")
  @Column(name = "mod_id")
  private String modId;

  /**
   * 광고사례노출여부
   */
  @ApiModelProperty("adv_case_expo")
  @Column(name = "adv_case_expo", nullable = false)
  private Boolean advCaseExpo;

  /**
   * 총슬롯
   */
  @ApiModelProperty("total_slot")
  @Column(name = "total_slot")
  private Integer totalSlot;

  /**
   * 배지코드
   */
  @ApiModelProperty("badge_code")
  @Column(name = "badge_code")
  private String badgeCode;

  public void setModifyReq(ProductBundleReq modReq) {
    this.seq = modReq.getSeq();
    this.name = modReq.getName();
    this.price = modReq.getPrice();
    this.description = modReq.getDescription();
    this.advCaseExpo = modReq.getAdvCaseExpo();
    this.operation = modReq.getOperation();
    this.memo = modReq.getMemo();
    this.badgeCode = modReq.getBadgeCode();
    this.modId = modReq.getModId();
    this.modDate = LocalDateUtils.krNow();
  }

  public Product(ProductBundleReq productBundleReq) {
    this.name = productBundleReq.getName();
    this.price = productBundleReq.getPrice();
    this.description = productBundleReq.getDescription();
    this.advCaseExpo = productBundleReq.getAdvCaseExpo();
    this.operation = productBundleReq.getOperation();
    this.memo = productBundleReq.getMemo();
    this.regId = productBundleReq.getRegId();
    this.badgeCode = productBundleReq.getBadgeCode();
    this.regDate = LocalDateUtils.krNow();
  }

}