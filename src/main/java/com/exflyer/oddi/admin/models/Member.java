package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.member.dto.MemberModReq;
import com.exflyer.oddi.admin.share.LocalDateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 회원(광고주) 정보
 */
@Data
@Entity
@Table(name = "member")
@ApiModel("회원(광고주) 정보")
public class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @ApiModelProperty("id")
  @Column(name = "id")
  private String id;

  /**
   * 이름
   */
  @ApiModelProperty("이름")
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * 비밀번호
   */
  @ApiModelProperty("비밀번호")
  @Column(name = "password", nullable = false)
  private String password;

  /**
   * 전화번호
   */
  @ApiModelProperty("전화번호")
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  /**
   * 수신 동의
   */
  @ApiModelProperty("수신 동의")
  @Column(name = "receive_consent", nullable = false)
  private Integer receiveConsent = 0;

  /**
   * 인증 코드
   */
  @ApiModelProperty("인증 코드")
  @Column(name = "auth_code")
  private String authCode;

  /**
   * 가입 날짜
   */
  @ApiModelProperty("가입 날짜")
  @Column(name = "signup_date", nullable = false)
  private LocalDateTime signupDate;

  /**
   * 변경 관리자 ID
   */
  @ApiModelProperty("변경 날짜")
  @Column(name = "mod_id")
  private String modId;

  /**
   * 변경 날짜
   */
  @ApiModelProperty("변경 날짜")
  @Column(name = "mod_date")
  private LocalDateTime modDate;

  /**
   * 상태 코드
   */
  @ApiModelProperty("상태 코드")
  @Column(name = "state_code", nullable = false)
  private String stateCode;

  /**
   * 비밀번호 오류 카운트
   */
  @ApiModelProperty("비밀번호 오류 카운트")
  @Column(name = "password_error_count", nullable = false)
  private Integer passwordErrorCount;

  /**
   * 비밀번호 변경 날짜
   */
  @ApiModelProperty("비밀번호 변경 날짜")
  @Column(name = "password_mod_date", nullable = false)
  private LocalDateTime passwordModDate;

  /**
   * 로그인 날짜
   */
  @ApiModelProperty("로그인 날짜")
  @Column(name = "login_date", nullable = false)
  private LocalDateTime loginDate;

  /**
   * 메모
   */
  @ApiModelProperty("메모")
  @Column(name = "memo", nullable = false)
  private String memo;

  /**
   * email
   */
  @ApiModelProperty("email")
  @Column(name = "email", nullable = false)
  private String email;


  public void setModifyReq(MemberModReq memberModReq) {
    this.stateCode = memberModReq.getStateCode();
    this.memo = memberModReq.getMemo();
    this.modId = memberModReq.getModId();
    this.modDate = LocalDateUtils.krNow();
  }
}
