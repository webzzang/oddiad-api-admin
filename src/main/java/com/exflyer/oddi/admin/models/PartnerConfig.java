package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 파트너 설정
 */
@Data
@Entity
@ApiModel("파트너 설정")
@Table(name = "partner_config")
public class PartnerConfig implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 종류(오디존, 지하철)
   */
  @Id
  @Column(name = "type")
  @ApiModelProperty("종류(오디존, 지하철)")
  private String type;

  /**
   * 슬롯 카운트
   */
  @ApiModelProperty("슬롯 카운트")
  @Column(name = "slot_count", nullable = false)
  private Integer slotCount;

  /**
   * 슬롯 영상 시간
   */
  @ApiModelProperty("슬롯 영상 시간")
  @Column(name = "slot_video_time")
  private Integer slotVideoTime;

  /**
   * 디자인 요청 여부
   */
  @ApiModelProperty("디자인 요청 여부")
  @Column(name = "design_request")
  private Boolean designRequest;

  /**
   * 화면 분할(1, 2, 3분할)
   */
  @Column(name = "display_div")
  @ApiModelProperty("화면 분할(1, 2, 3분할)")
  private String displayDiv;

  /**
   * 측면 화면 서비스 코드
   */
  @ApiModelProperty("측면 화면 서비스 코드")
  @Column(name = "side_display_service_code")
  private String sideDisplayServiceCode;

  /**
   * 하단 화면 서비스 코드
   */
  @ApiModelProperty("하단 화면 서비스 코드")
  @Column(name = "bottom_display_service_code")
  private String bottomDisplayServiceCode;

  /**
   * 운영시작시간
   */
  @Column(name = "operation_start_time")
  @ApiModelProperty("운영시작시간")
  private String operationStartTime;

  /**
   * 운영종료시간
   */
  @Column(name = "operation_end_time")
  @ApiModelProperty("운영종료시간")
  private String operationEndTime;

  /**
   * 기본 광고 파일명
   */
  @ApiModelProperty("기본 광고 파일명")
  @Column(name = "adv_name")
  private String advName;

  /**
   * 오디존 광고 시작 가능일(from)
   */
  @ApiModelProperty("오디존 광고 시작 가능일(from)")
  @Column(name = "oddi_adv_from_start_date")
  private Integer oddiAdvFromStartDate;

  /**
   * 오디존 광고 시작 가능일(to)
   */
  @ApiModelProperty("오디존 광고 시작 가능일(to)")
  @Column(name = "oddi_adv_to_start_date")
  private Integer oddiAdvToStartDate;

  /**
   * 오디존 최장 광고기간
   */
  @ApiModelProperty("오디존 최장 광고기간")
  @Column(name = "oddi_adv_max_date")
  private Integer oddiAdvMaxDate;

  /**
   * 오디존 광고 신청취소 가능일
   */
  @ApiModelProperty("오디존 광고 신청취소 가능일")
  @Column(name = "oddi_adv_cancel_date")
  private Integer oddiAdvCancelDate;

  /**
   * 지하철 익월 광고 마감일
   */
  @ApiModelProperty("지하철 익월 광고 마감일")
  @Column(name = "subway_adv_last_date")
  private Integer subwayAdvLastDate;

  /**
   * 지하철 최대광고 시작일
   */
  @ApiModelProperty("지하철 최대광고 시작일")
  @Column(name = "subway_adv_max_start_date")
  private Integer subwayAdvMaxStartDate;

  /**
   * 지하철 광고 신청취소 가능일
   */
  @ApiModelProperty("지하철 광고 신청취소 가능일")
  @Column(name = "subway_adv_cancel_date")
  private Integer subwayAdvCancelDate;

  /**
   * 지하철 최장 광고기간
   */
  @ApiModelProperty("지하철 최장 광고기간")
  @Column(name = "subway_adv_max_date")
  private Integer subwayAdvMaxDate;

  /*public PartnerConfig(OddiZoneConfigReq saveReq) {
    this.slotCount = saveReq.getSlotCount();
    this.slotVideoTime = saveReq.getSlotVideoTime();
    this.advStartDate = saveReq.getAdvStartDate();
    this.advEndDate = saveReq.getAdvEndDate();
    this.designRequest = saveReq.getDesignRequest();
    this.displayDiv = saveReq.getDisplayDiv();
    this.sideDisplayServiceCode = saveReq.getSideDisplayServiceCode();
    this.bottomDisplayServiceCode = saveReq.getBottomDisplayServiceCode();
    this.defaultAdvFileSeq = saveReq.getDefaultAdvFileSeq();
    this.advName = saveReq.getAdvName();
    this.type = saveReq.getType();
    this.operationStartTime = saveReq.getOperationStartTime();
    this.operationEndTime = saveReq.getOperationEndTime();
  }*/

}
