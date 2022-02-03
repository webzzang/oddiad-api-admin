package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.contents.livestream.dto.LiveStreamingReq;
import com.exflyer.oddi.admin.share.LocalDateUtils;
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
import lombok.NoArgsConstructor;

/**
 * 라이브 일정
 */
@Data
@Entity
@ApiModel("라이브 일정")
@Table(name = "live_schedule")
@NoArgsConstructor
public class LiveSchedule implements Serializable {

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
   * 제목
   */
  @ApiModelProperty("제목")
  @Column(name = "title", nullable = false)
  private String title;

  /**
   * 운영일
   */
  @ApiModelProperty("운영일")
  @Column(name = "operation_day", nullable = false)
  private String operationDay;

  /**
   * 시작시간
   */
  @ApiModelProperty("시작시간")
  @Column(name = "start_time", nullable = false)
  private String startTime;

  /**
   * 종료시간
   */
  @ApiModelProperty("종료시간")
  @Column(name = "end_time", nullable = false)
  private String endTime;

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

  public void setModifyLiveSchedule(LiveStreamingReq param){
    this.title = param.getTitle();
    this.operationDay = param.getOperationDay();
    this.startTime = param.getStartTime();
    this.endTime = param.getEndTime();
    this.modId = param.getModId();
    this.modDate = LocalDateUtils.krNow();
  }

  public LiveSchedule(LiveStreamingReq param){
    this.title = param.getTitle();
    this.operationDay = param.getOperationDay();
    this.startTime = param.getStartTime();
    this.endTime = param.getEndTime();
    this.regId = param.getRegId();
    this.regDate = LocalDateUtils.krNow();
  }

}
