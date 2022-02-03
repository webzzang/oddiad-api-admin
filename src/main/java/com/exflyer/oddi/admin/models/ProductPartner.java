package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 상품 파트너
 */
@Data
@Entity
@ApiModel("상품 파트너")
@Table(name = "product_partner")
@IdClass(ProductPartner.ID.class)
public class ProductPartner implements Serializable {

  private static final long serialVersionUID = 1L;

//  @EmbeddedId
//  private ProductPartnerPk productPartnerPk;

  /**
   * 상품 순번
   */
  @ApiModelProperty("상품 순번")
  @Column(name = "product_seq", nullable = false)
  @Id
  private Long productSeq;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  @Id
  private Long partnerSeq;

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

  @Data
  static class ID implements Serializable {
    private Long productSeq;
    private Long partnerSeq;
  }

}
