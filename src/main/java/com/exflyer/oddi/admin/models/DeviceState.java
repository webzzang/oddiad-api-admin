package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 장비 상태
 */
@Data
@Entity
@ApiModel("장비 상태")
@Table(name = "device_state")
public class DeviceState implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 장비 id
   */
  @Id
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
  @Column(name = "error_code", nullable = false)
  private String errorCode;

  /**
   * 동작 코드
   */
  @ApiModelProperty("동작 코드")
  @Column(name = "action_code", nullable = false)
  private String actionCode;

  /**
   * 변경 날짜
   */
  @ApiModelProperty("변경 날짜")
  @Column(name = "mod_date", nullable = false)
  private LocalDateTime modDate;

}
