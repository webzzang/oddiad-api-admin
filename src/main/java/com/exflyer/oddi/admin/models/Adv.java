package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.adv.audit.dto.AdvAuditModReq;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 광고
 */
@Data
@Entity
@ApiModel("광고")
@Table(name = "adv")
public class Adv implements Serializable {

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
   * 회원 id
   */
  @ApiModelProperty("회원 id")
  @Column(name = "member_id")
  private String memberId;

  /**
   * 제목
   */
  @Column(name = "title")
  @ApiModelProperty("제목")
  private String title;

  /**
   * 업종 종류 코드
   */
  @ApiModelProperty("업종 종류 코드")
  @Column(name = "business_type_code")
  private String businessTypeCode;

  /**
   * 시작 날짜
   */
  @ApiModelProperty("시작 날짜")
  @Column(name = "start_date")
  private String startDate;

  /**
   * 종료 날짜
   */
  @Column(name = "end_date")
  @ApiModelProperty("종료 날짜")
  private String endDate;

  /**
   * 금액
   */
  @Column(name = "price")
  @ApiModelProperty("금액")
  private BigDecimal price;

  /**
   * 할인 금액
   */
  @ApiModelProperty("할인 금액")
  @Column(name = "discount_price")
  private BigDecimal discountPrice;

  /**
   * 수정 날짜
   */
  @Column(name = "reg_date")
  @ApiModelProperty("수정 날짜")
  private LocalDateTime regDate;

  /**
   * 변경 날짜
   */
  @Column(name = "mod_date")
  @ApiModelProperty("변경 날짜")
  private LocalDateTime modDate;

  /**
   * 심사 코드
   */
  @ApiModelProperty("심사 코드")
  @Column(name = "audit_code")
  private String auditCode;

  /**
   * 진행 코드(심사중, 결제전, 결제후 등)
   */
  @Column(name = "progress_code")
  @ApiModelProperty("진행 코드(심사중, 결제전, 결제후 등)")
  private String progressCode;

  /**
   * 쿠폰 번호
   */
  @ApiModelProperty("쿠폰 번호")
  @Column(name = "coupon_number")
  private String couponNumber;

  /**
   * 결제 순번
   */
  @ApiModelProperty("결제 순번")
  @Column(name = "payment_seq")
  private Long paymentSeq;

  /**
   * 거절 이유
   */
  @ApiModelProperty("거절 이유")
  @Column(name = "rejection_reason")
  private String rejectionReason;

  /**
   * 거절 코드
   */
  @ApiModelProperty("거절 코드")
  @Column(name = "rejection_code")
  private String rejectionCode;

  /**
   * 거절 날짜
   */
  @ApiModelProperty("거절 날짜")
  @Column(name = "rejection_date")
  private LocalDateTime rejectionDate;

  /**
   * 심사 id
   */
  @Column(name = "audit_id")
  @ApiModelProperty("심사 id")
  private String auditId;

  /**
   * 디자인 요청 여부
   */
  @ApiModelProperty("디자인 요청 여부")
  @Column(name = "design_request")
  private Integer designRequest = 0;

  /**
   * 채널 종류(오디존, 지하철)
   */
  @Column(name = "channel_type")
  @ApiModelProperty("채널 종류(오디존, 지하철)")
  private String channelType;

  /**
   * 메모
   */
  @Column(name = "memo")
  @ApiModelProperty("메모")
  private String memo;

  /**
   * 승인 날짜
   */
  @ApiModelProperty("승인 날짜")
  @Column(name = "approval_date")
  private LocalDateTime approvalDate;

  /**
   * 노출
   */
  @Column(name = "expo")
  @ApiModelProperty("노출")
  private Boolean expo;

  /**
   * 사용자 알림 확인여부
   */
  @Column(name = "user_check")
  @ApiModelProperty("사용자 알림 확인여부")
  private Boolean userCheck = false;

  public void setModifyReq(AdvAuditModReq advAuditModReq){
    this.memo = advAuditModReq.getMemo();
    this.rejectionCode = advAuditModReq.getRejectionCode();
    this.rejectionReason = advAuditModReq.getRejectionReason();
    this.modDate = LocalDateUtils.krNow();
  }

  public void setAuditReq(AdvAuditModReq advAuditModReq){
    this.memo = advAuditModReq.getMemo();
    this.auditId = advAuditModReq.getAuditId();
    this.auditCode = advAuditModReq.getAuditCode();
    this.approvalDate = LocalDateUtils.krNow();
    this.modDate = LocalDateUtils.krNow();
  }

  public void setRejectionReq(AdvAuditModReq advAuditModReq){
    this.memo = advAuditModReq.getMemo();
    this.auditId = advAuditModReq.getAuditId();
    this.auditCode = advAuditModReq.getAuditCode();
    this.rejectionCode = advAuditModReq.getRejectionCode();
    this.rejectionReason = advAuditModReq.getRejectionReason();
    this.rejectionDate = LocalDateUtils.krNow();
    this.modDate = LocalDateUtils.krNow();
  }

}
