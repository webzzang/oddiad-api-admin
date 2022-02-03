package com.exflyer.oddi.admin.models;

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

/**
 * 광고 재편성
 */
@Data
@Entity
@ApiModel("광고 재편성")
@Table(name = "adv_partner_organize")
public class AdvPartnerOrganize implements Serializable {

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
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  private Long partnerSeq;

  /**
   * 장비 ID
   */
  @ApiModelProperty("장비 ID")
  @Column(name = "device_id", nullable = false)
  private String deviceId;

  /**
   * 디바이스 푸시 처리여부
   */
  @ApiModelProperty("디바이스 푸시 처리여부")
  @Column(name = "device_push")
  private Boolean devicePush = false;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  /**
   * 생성 ID
   */
  @ApiModelProperty("생성 ID")
  @Column(name = "reg_id")
  private String regId;


}
