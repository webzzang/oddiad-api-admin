package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.notification.send.dto.NotificationReq;
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
import lombok.NoArgsConstructor;

/**
 * 알림_group
 */
@Data
@Entity
@ApiModel("알림_group")
@Table(name = "notification_group")
@NoArgsConstructor
public class NotificationGroup implements Serializable {

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
   * 알림 대상 그룹순번
   */
  @Column(name = "target_group_seq")
  @ApiModelProperty("알림 대상 그룹순번")
  private Long targetGroupSeq;

  /**
   * 내용
   */
  @ApiModelProperty("내용")
  @Column(name = "contents")
  private String contents;

  /**
   * 광고 메세지 여부
   */
  @Column(name = "adv_message")
  @ApiModelProperty("광고 메세지 여부")
  private Boolean advMessage;

  /**
   * 발신자 전화번호
   */
  @Column(name = "sender_phone_number")
  @ApiModelProperty("발신자 전화번호")
  private String senderPhoneNumber;

  /**
   * 발송 시간(즉시 일경우 0)
   */
  @Column(name = "send_time")
  @ApiModelProperty("발송 시간(즉시 일경우 0)")
  private String sendTime;

  /**
   * 자동 여부
   */
  @ApiModelProperty("자동 여부")
  @Column(name = "auto", nullable = false)
  private Boolean auto = false;

  /**
   * 성공 카운트
   */
  @ApiModelProperty("성공 카운트")
  @Column(name = "success_count")
  private BigDecimal successCount;

  /**
   * 실패 카운트
   */
  @ApiModelProperty("실패 카운트")
  @Column(name = "fail_count")
  private BigDecimal failCount;

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
   * 완료
   */
  @ApiModelProperty("완료")
  @Column(name = "done", nullable = false)
  private Boolean done;

  /**
   * 알림톡
   */
  @ApiModelProperty("알림톡")
  @Column(name = "alrim_talk", nullable = false)
  private Boolean alrimTalk;

  /**
   * 대상생성완료
   */
  @ApiModelProperty("대상생성완료")
  @Column(name = "target_create_done")
  private Boolean target_create_done = false;

  /**
   * 템플릿 코드
   */
  @ApiModelProperty("템플릿 코드")
  @Column(name = "template_id")
  private String templateId;

  public NotificationGroup(NotificationReq param) {
    this.targetGroupSeq = param.getTargetGroupSeq();
    this.contents = param.getContents();
    this.advMessage = param.getAdvMessage();
    this.sendTime = param.getReservationDate();
    this.senderPhoneNumber = param.getSendPhoneNumber();
    this.auto = param.getAuto();
    this.regDate = LocalDateUtils.krNow();
    this.regId = param.getRegId();
    this.done = param.getDone();
    this.alrimTalk = param.getAlrimTalk();
    this.target_create_done = param.getTargetCreateDone();
  }

}
