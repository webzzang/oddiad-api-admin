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
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

/**
 * 파트너 지하철 노선
 */
@Data
@Entity
@ApiModel("파트너 지하철 노선")
@Table(name = "partner_subway")
public class PartnerSubway implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 순번
   */
  @Id
  @ApiModelProperty("순번")
  @Column(name = "subway_seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long subwaySeq;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  private Long partnerSeq;

  /**
   * 지하철 노선코드
   */
  @ApiModelProperty("지하철 노선코드")
  @Column(name = "subway_code")
  private String subwayCode;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  /**
   * 생성 id
   */
  @ApiModelProperty("생성 id")
  @Column(name = "reg_id")
  private String regId;


}
