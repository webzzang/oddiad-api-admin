package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.notification.send.dto.NotificationReq;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
 * 알림
 */
@Data
@Entity
@ApiModel("알림")
@Table(name = "notification")
@NoArgsConstructor
public class Notification implements Serializable {

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
     * 그룹 순번
     */
    @ApiModelProperty("그룹 순번")
    @Column(name = "group_seq", nullable = false)
    private Long groupSeq;

    /**
     * 수신자 ID
     */
    @ApiModelProperty("수신자 ID")
    @Column(name = "receive_id")
    private String receiveId;

    /**
     * 수신자 이름
     */
    @ApiModelProperty("수신자 이름")
    @Column(name = "receive_name", nullable = false)
    private String receiveName;

    /**
     * 수신자 전화 번호
     */
    @ApiModelProperty("수신자 전화 번호")
    @Column(name = "receive_phone_number", nullable = false)
    private String receivePhoneNumber;

    /**
     * 내용
     */
    @ApiModelProperty("내용")
    @Column(name = "contents", nullable = false)
    private String contents;

    /**
     * 발송 시간(즉시 일경우 0)
     */
    @ApiModelProperty("발송 시간(즉시 일경우 0)")
    @Column(name = "send_time", nullable = false)
    private String sendTime;

    /**
     * 발신자 id
     */
    @ApiModelProperty("발신자 id")
    @Column(name = "sender_id", nullable = false)
    private String senderId;

    /**
     * 발신자 이름
     */
    @ApiModelProperty("발신자 이름")
    @Column(name = "sender_name", nullable = false)
    private String senderName;

    /**
     * 발신자 전화 번호
     */
    @ApiModelProperty("발신자 전화 번호")
    @Column(name = "sender_phone_number")
    private String senderPhoneNumber;

    /**
     * 알림톡 여부
     */
    @ApiModelProperty("알림톡 여부")
    @Column(name = "alrim_talk")
    private Boolean alrimTalk = false;

    /**
     * 등록일자
     */
    @ApiModelProperty("등록일자")
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    /**
     * 카카오 템플릿 ID
     */
    @ApiModelProperty("카카오 템플릿 ID")
    @Column(name = "kakao_template_id")
    private String kakaoTemplateId;

    public Notification(NotificationReq param) {
        this.groupSeq = param.getTargetGroupSeq();
        this.receiveId = param.getMemberId();
        this.receiveName = param.getMemberName();
        this.receivePhoneNumber = param.getSendPhoneNumber();
        this.contents = param.getContents();
        this.sendTime = param.getReservationDate();
        this.senderId = param.getRegId();
        this.senderName = param.getRegName();
        this.senderPhoneNumber = param.getFromPhoneNumber();
        this.regDate = LocalDateUtils.krNow();
    }

}
