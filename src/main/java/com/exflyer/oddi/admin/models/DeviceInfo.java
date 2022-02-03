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
 * 장비
 */
@Data
@Entity
@ApiModel("장비")
@Table(name = "device_info")
public class DeviceInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 디바이스ID
   */
  @Id
  @ApiModelProperty("device_id")
  @Column(name = "device_id", nullable = false)
  private String deviceId;

  /**
   * 토큰(PUSH 발송용)
   */
  @ApiModelProperty("토큰(PUSH 발송용)")
  @Column(name = "fcm_token", nullable = false)
  private String fcmToken;

  /**
   * 디바이스 모델명 (ex. TCL tv, Chromecast)
   */
  @ApiModelProperty("디바이스 모델명 (ex. TCL tv, Chromecast)")
  @Column(name = "device_model", nullable = false)
  private String deviceModel;

  /**
   * 디바이스에 설치된 Android id 값(os 가 재설치 되기 전까지 유니크)
   */
  @ApiModelProperty("디바이스에 설치된 Android id 값(os 가 재설치 되기 전까지 유니크)")
  @Column(name = "android_id", nullable = false)
  private String androidId;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 생성 id
   */
  @ApiModelProperty("생성 id")
  @Column(name = "reg_id", nullable = false)
  private String regId;

  /**
   * 변경 날짜
   */
  @Column(name = "mod_date")
  @ApiModelProperty("변경 날짜")
  private LocalDateTime modDate;

  /**
   * 변경 id
   */
  @Column(name = "mod_id")
  @ApiModelProperty("변경 id")
  private String modId;

}
