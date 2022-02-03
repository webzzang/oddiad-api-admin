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
 * 광고 보류 로그
 */
@Data
@Entity
@ApiModel("광고 보류 로그")
@Table(name = "adv_reject_log")
public class AdvRejectLog implements Serializable {

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
   * 광고 신청일시
   */
  @ApiModelProperty("광고 신청일시")
  @Column(name = "adv_reg_date", nullable = false)
  private LocalDateTime advRegDate;

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
   * 생성(거절) 날짜
   */
  @ApiModelProperty("생성(거절) 날짜")
  @Column(name = "reg_date")
  private LocalDateTime regDate;

  /**
   * 생성(거절) id
   */
  @ApiModelProperty("생성(거절) id")
  @Column(name = "reg_id")
  private String regId;


}
