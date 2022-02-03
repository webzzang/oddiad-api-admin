package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * 결제
 */
@Data
@Entity
@ApiModel("결제")
@Table(name = "payment")
public class Payment implements Serializable {

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
   * 종류(결제, 취소)
   */
  @ApiModelProperty("종류(결제, 취소)")
  @Column(name = "type", nullable = false)
  private String type;

  /**
   * 회원 id
   */
  @ApiModelProperty("회원 id")
  @Column(name = "member_id", nullable = false)
  private String memberId;

  /**
   * 광고 순번
   */
  @ApiModelProperty("광고 순번")
  @Column(name = "adv_seq", nullable = false)
  private Long advSeq;

  /**
   * 금액
   */
  @ApiModelProperty("금액")
  @Column(name = "price", nullable = false)
  private BigDecimal price;

  /**
   * 채널 종류(오디존, 지하철)
   */
  @ApiModelProperty("채널 종류(오디존, 지하철)")
  @Column(name = "channel_type", nullable = false)
  private String channelType;

  /**
   * 상품 이름
   */
  @ApiModelProperty("상품 이름")
  @Column(name = "product_name", nullable = false)
  private String productName;

  /**
   * 광고 이름
   */
  @ApiModelProperty("광고 이름")
  @Column(name = "adv_name", nullable = false)
  private String advName;

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

  /**
   * 응답 코드(PG 연동)
   */
  @Column(name = "response_code")
  @ApiModelProperty("응답 코드(PG 연동)")
  private String responseCode;

  /**
   * 응답 메세지(PG 연동)
   */
  @Column(name = "response_message")
  @ApiModelProperty("응답 메세지(PG 연동)")
  private String responseMessage;

  /**
   * 연동 날짜(PG 연동)
   */
  @Column(name = "sync_date")
  @ApiModelProperty("연동 날짜(PG 연동)")
  private LocalDateTime syncDate;

  /**
   * 성공 여부(PG 연동)
   */
  @Column(name = "success")
  @ApiModelProperty("성공 여부(PG 연동)")
  private Integer success;

}
