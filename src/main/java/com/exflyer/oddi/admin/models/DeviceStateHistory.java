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
 * 장비 상태 이력
 */
@Data
@Entity
@ApiModel("장비 상태 이력")
@Table(name = "device_state_history")
public class DeviceStateHistory implements Serializable {

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
   * 장비 id
   */
  @ApiModelProperty("장비 id")
  @Column(name = "device_id", nullable = false)
  private String deviceId;

  /**
   * 상태 코드
   */
  @ApiModelProperty("상태 코드")
  @Column(name = "state_code")
  private String stateCode;

  /**
   * 오류 코드
   */
  @ApiModelProperty("오류 코드")
  @Column(name = "error_code")
  private String errorCode;

  /**
   * 광고 순번
   */
  @Column(name = "adv_seq")
  @ApiModelProperty("광고 순번")
  private Long advSeq;

  /**
   * 동작 코드
   */
  @ApiModelProperty("동작 코드")
  @Column(name = "action_code")
  private String actionCode;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date")
  private LocalDateTime regDate;

}
