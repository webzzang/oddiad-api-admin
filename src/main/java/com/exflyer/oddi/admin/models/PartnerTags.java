package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
 * 파트너 태그
 */
@Data
@Entity
@ApiModel("파트너 태그")
@Table(name = "partner_tags")
@IdClass(PartnerTags.ID.class)
public class PartnerTags implements Serializable {

  private static final long serialVersionUID = 1L;


//  @EmbeddedId
//  private PartnerTagsPk partnerTagsPk;


  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  @Id
  private Long partnerSeq;

  /**
   * 태그 순번
   */
  @ApiModelProperty("태그 순번")
  @Column(name = "tag_seq", nullable = false)
  @Id
  private Long tagSeq;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 태그 노출 순서
   */
  @ApiModelProperty("태그 노출 순서")
  @Column(name = "view_order")
  private Integer viewOrder;


  @Data
  static class ID implements Serializable {
    private Long partnerSeq;
    private Long tagSeq;
  }

}
