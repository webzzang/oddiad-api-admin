package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 광고_광고처
 */
@Data
@Entity
@ApiModel("광고_광고처")
@Table(name = "adv_partner")
@IdClass(AdvPartner.ID.class)
public class AdvPartner implements Serializable {

  private static final long serialVersionUID = 1L;

//  @EmbeddedId
//  private AdvPartnerPk advPartnerPk;

  /**
   * 광고 순번
   */
  @ApiModelProperty("광고 순번")
  @Column(name = "adv_seq", nullable = false)
  @Id
  private Long advSeq;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  @Id
  private Long partnerSeq;

  /**
   * 광고 슬롯
   */
  @ApiModelProperty("광고 슬롯")
  @Column(name = "request_slot", nullable = false)
  private Integer requestSlot;

  /**
   * 노출
   */
  @ApiModelProperty("노출")
  @Column(name = "expo")
  private Boolean expo;

  /**
   * 노출 순서
   */
  @ApiModelProperty("노출 순서")
  @Column(name = "view_order")
  private Integer viewOrder;

  @Data
  static class ID implements Serializable {
    private Long advSeq;
    private Long partnerSeq;
  }

}
