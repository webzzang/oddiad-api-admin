package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 장비 제어
 */
@Data
@Entity
@ApiModel("장비 제어")
@Table(name = "device_control")
public class DeviceControl implements Serializable {

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
   * 제어 코드
   */
  @ApiModelProperty("제어 코드")
  @Column(name = "control_code", nullable = false)
  private String controlCode;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 성공 여부
   */
  @ApiModelProperty("성공 여부")
  @Column(name = "success", nullable = false)
  private Integer success;

  /**
   * 오류 코드
   */
  @ApiModelProperty("오류 코드")
  @Column(name = "error_code", nullable = false)
  private String errorCode;

}
