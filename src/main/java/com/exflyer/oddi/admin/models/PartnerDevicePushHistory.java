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
 * 파트너 기기 송출 로그
 */
@Data
@Entity
@ApiModel("파트너 기기 송출 로그")
@Table(name = "partner_device_push_history")
public class PartnerDevicePushHistory implements Serializable {

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
   * 디바이스 ID
   */
  @ApiModelProperty("디바이스 ID")
  @Column(name = "device_id")
  private String deviceId;

  /**
   * 액션코드
   */
  @ApiModelProperty("액션코드")
  @Column(name = "action")
  private String action;

  /**
   * 스크린 타입
   */
  @ApiModelProperty("스크린 타입")
  @Column(name = "screen_type")
  private String screenType;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  /**
   * 성공여부
   */
  @ApiModelProperty("성공여부")
  @Column(name = "success")
  private Integer success;

  /**
   * 실패사유
   */
  @ApiModelProperty("실패사유")
  @Column(name = "fail_message")
  private String failMessage;

}
