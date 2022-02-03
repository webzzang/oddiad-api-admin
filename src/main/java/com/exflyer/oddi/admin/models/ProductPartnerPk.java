package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 상품 파트너
 */
@Data
@Embeddable
public class ProductPartnerPk implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 상품 순번
   */
  @ApiModelProperty("상품 순번")
  @Column(name = "product_seq", nullable = false)
  private Long productSeq;

  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  private Long partnerSeq;

}
