package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 파트너 장비
 */
@Data
@Entity
@ApiModel("파트너 장비")
@Table(name = "partner_device")
public class PartnerDevice implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 장비 id
   */
  @Id
  @ApiModelProperty("장비 id")
  @Column(name = "device_id", nullable = false)
  private String deviceId;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  private Long partnerSeq;

  /**
   * 이름
   */
  @ApiModelProperty("이름")
  @Column(name = "name")
  private String name;

  /**
   * 화면분할코드
   */
  @ApiModelProperty("화면분할코드")
  @Column(name = "display_div")
  private String displayDiv;

  /**
   * 오른쪽 화면 코드
   */
  @ApiModelProperty("오른쪽 화면 코드")
  @Column(name = "side_contents_type")
  private String sideContentsType;

  /**
   * 하단 화면 코드
   */
  @ApiModelProperty("하단 화면 코드")
  @Column(name = "bottom_contents_type")
  private String bottomContentsType;

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

  /**
   * 기본광고 노출여부
   */
  @ApiModelProperty("기본광고 노출여부")
  @Column(name = "default_adv_expo")
  private Boolean defaultAdvExpo;

}
