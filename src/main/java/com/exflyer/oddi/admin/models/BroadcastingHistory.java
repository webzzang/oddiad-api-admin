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
 * 방송 이력
 */
@Data
@Entity
@ApiModel("방송 이력")
@Table(name = "broadcasting_history")
public class BroadcastingHistory implements Serializable {

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
   * 채널 종류
   */
  @ApiModelProperty("채널 종류")
  @Column(name = "channel_type", nullable = false)
  private String channelType;

  /**
   * 파트너 이름
   */
  @ApiModelProperty("파트너 이름")
  @Column(name = "partner_name", nullable = false)
  private String partnerName;

  /**
   * 장비 이름
   */
  @ApiModelProperty("장비 이름")
  @Column(name = "device_name", nullable = false)
  private String deviceName;

  /**
   * 회원 id
   */
  @ApiModelProperty("회원 id")
  @Column(name = "member_id", nullable = false)
  private String memberId;

  /**
   * 광고주
   */
  @ApiModelProperty("광고주")
  @Column(name = "advertisers", nullable = false)
  private String advertisers;

  /**
   * 광고 시작 날짜
   */
  @ApiModelProperty("광고 시작 날짜")
  @Column(name = "adv_start_date", nullable = false)
  private String advStartDate;

  /**
   * 광고 종료 날짜
   */
  @ApiModelProperty("광고 종료 날짜")
  @Column(name = "adv_end_date", nullable = false)
  private String advEndDate;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

}
