package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 파트너 기본광고 노출여부
 */
@Data
@Entity
@ApiModel("파트너 기본광고 노출여부")
@Table(name = "partner_default_adv")
@IdClass(PartnerDefaultAdv.ID.class)
public class PartnerDefaultAdv implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 종류(오디존, 지하철)
   */
  @Id
  @Column(name = "channel_type")
  @ApiModelProperty("종류(오디존, 지하철)")
  private String channelType;

  /**
   * 파트너순번
   */
  @Id
  @Column(name = "partner_seq")
  @ApiModelProperty("파트너순번")
  private Long partnerSeq;

  /**
   * 노출
   */
  @Column(name = "expo")
  @ApiModelProperty("노출")
  private Boolean expo;

  @Data
  static class ID implements Serializable {
    private String channelType;
    private Long partnerSeq;
  }


}
