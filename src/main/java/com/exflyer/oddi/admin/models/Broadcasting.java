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
 * 방송
 */
@Data
@Entity
@ApiModel("방송")
@Table(name = "broadcasting")
public class Broadcasting implements Serializable {

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
   * 광고 순번
   */
  @ApiModelProperty("광고 순번")
  @Column(name = "adv_seq", nullable = false)
  private Long advSeq;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  private Long partnerSeq;

  /**
   * 플레이어 시리얼
   */
  @ApiModelProperty("플레이어 시리얼")
  @Column(name = "device_id", nullable = false)
  private String deviceId;

  /**
   * 수정 날짜
   */
  @ApiModelProperty("수정 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 변경 id
   */
  @Column(name = "mod_id")
  @ApiModelProperty("변경 id")
  private String modId;

  /**
   * 변경 날짜
   */
  @Column(name = "mod_date")
  @ApiModelProperty("변경 날짜")
  private LocalDateTime modDate;

  /**
   * 노출 여부
   */
  @Column(name = "expo")
  @ApiModelProperty("노출 여부")
  private Boolean expo = true;

  /**
   * 정렬 번호
   */
  @Column(name = "ordering")
  @ApiModelProperty("정렬 번호")
  private Integer ordering;

  /**
   * 영상 시간
   */
  @ApiModelProperty("영상 시간")
  @Column(name = "video_time", nullable = false)
  private Integer videoTime;

}
