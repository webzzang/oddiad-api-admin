package com.exflyer.oddi.admin.api.notification.send.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
public class NotificationReq {

  @ApiModelProperty(value = "발송 그룹명", position = 1)
  private String name;

  @ApiModelProperty(value = "발송 대상코드(그룹코드:NTC(전체회원,전체광고주,전체오디존광고주, 전체지하철광고주, 오디존광고주,지하철광고주,회원검색,전화번호입력 등)", position = 2)
  private String targetCode;

  @ApiModelProperty(value = "발송대상 - 오디존, 지하철 파트너 순번 객체리스트(전체일경우에는 해당안됨)", position = 3)
  private List<PartnerReq> partnerList;

  @ApiModelProperty(value = "발송 회원 아이디, 이름 객체리스트", position = 4)
  private List<MemberNotificationReq> memberIdList;

  @ApiModelProperty(value = "발송 전화번호", position = 5)
  private List<String> sendPhoneNumberList;

  @ApiModelProperty(value = "발송구분(MST, 즉시:MST001, 예약:MST002)코드", position = 6)
  private String messageSendType;

  @ApiModelProperty(value = "예약날짜(yyyyMMddHHmmss)", position = 7)
  private String reservationDate;

  @ApiModelProperty(value = "발송타입코드(MTC, 광고성:MTC001, 정보성:MTC002)", position = 8)
  private String messageTypeCode;

  @ApiModelProperty(value = "메세지 내용", position = 9)
  private String contents;

  @ApiModelProperty(value = "등록자 ID", hidden = true)
  private String regId;

  @ApiModelProperty(value = "광고 메세지 여부", hidden = true)
  private Boolean advMessage;

  @ApiModelProperty(value = "자동여부", hidden = true)
  private Boolean auto = false;

  @ApiModelProperty(value = "완료여부", hidden = true)
  private Boolean done = false;

  @ApiModelProperty(value = "알림톡", hidden = true)
  private Boolean alrimTalk = false;

  @ApiModelProperty(value = "대상 생성완료", hidden = true)
  private Boolean targetCreateDone = false;

  @ApiModelProperty(value = "등록자명", hidden = true)
  private String regName;

  @ApiModelProperty(value = "알림대상 그룹순번", hidden = true)
  private Long targetGroupSeq;

  @ApiModelProperty(value = "발송 회원명", hidden = true)
  private String memberName;

  @ApiModelProperty(value = "발송 회원 아이디", hidden = true)
  private String memberId;

  @ApiModelProperty(value = "발송 전화번호", hidden = true)
  private String sendPhoneNumber;

  @ApiModelProperty(value = "발신 전화번호", hidden = true)
  private String fromPhoneNumber;

  @ApiModelProperty(value = "요청 시간", hidden = true)
  private String requestTime;

  @ApiModelProperty(value = "응답 코드", hidden = true)
  private String responseCode;

  @ApiModelProperty(value = "응답 메세지", hidden = true)
  private String responseMessage;

  @ApiModelProperty(value = "성공여부", hidden = true)
  private Boolean success;


  /*@Data
  public class PartnerList {
    private Long partnerSeq;
    private String partnerName;
  }*/

}
