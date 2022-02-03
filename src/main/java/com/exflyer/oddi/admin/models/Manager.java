package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountModReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerAccountRegReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModPasswordReq;
import com.exflyer.oddi.admin.api.manager.account.dto.ManagerMyAccountModReq;
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
import lombok.NoArgsConstructor;

/**
 * 관리자
 */
@Data
@Entity
@ApiModel("관리자")
@Table(name = "manager")
@NoArgsConstructor
public class Manager implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @ApiModelProperty("id")
  @Column(name = "id", nullable = false)
  private String id;

  /**
   * 역활순번
   */
  @ApiModelProperty("역활순번")
  @Column(name = "role_seq", nullable = false)
  private Long roleSeq;

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
  @Column(name = "phone_number")
  private String phoneNumber;

  /**
   * 비밀번호변경날짜
   */
  @ApiModelProperty("비밀번호변경날짜")
  @Column(name = "password_mod_date")
  private LocalDateTime passwordModDate;

  /**
   * 비밀번호오류카운트
   */
  @ApiModelProperty("비밀번호오류카운트")
  @Column(name = "password_error_count")
  private Integer passwordErrorCount;

  /**
   * 메모
   */
  @Column(name = "memo")
  @ApiModelProperty("메모")
  private String memo;

  /**
   * 상태
   */
  @Column(name = "state")
  @ApiModelProperty("상태")
  private String state;

  /**
   * 인증 번호
   */
  @Column(name = "auth_number")
  @ApiModelProperty("인증 번호")
  private String authNumber;

  /**
   * 생성 날짜
   */
  @Column(name = "reg_date")
  @ApiModelProperty("생성 날짜")
  private LocalDateTime regDate;

  /**
   * 생성 id
   */
  @Column(name = "reg_id")
  @ApiModelProperty("생성 id")
  private String regId;

  /**
   * 수정 날짜
   */
  @Column(name = "mod_date")
  @ApiModelProperty("수정 날짜")
  private LocalDateTime modDate;

  /**
   * 수정 id
   */
  @Column(name = "mod_id")
  @ApiModelProperty("수정 id")
  private String modId;

  @Column(name = "login_date")
  @ApiModelProperty("로그인 날짜")
  private LocalDateTime loginDate;

  @Column(name = "init_password")
  @ApiModelProperty("초기 비밀 번호 여부")
  private boolean initPassword;

  public Manager(ManagerAccountRegReq managerAccountRegReq) {
    this.id = managerAccountRegReq.getId();
    this.state = managerAccountRegReq.getStateCode();
    this.roleSeq = managerAccountRegReq.getRoleSeq();
    this.name = managerAccountRegReq.getName();
    this.memo = managerAccountRegReq.getMemo();
    this.phoneNumber = managerAccountRegReq.getPhoneNumber();
    this.regDate = LocalDateUtils.krNow();
    this.passwordModDate = LocalDateUtils.krNow();
    this.passwordErrorCount = 0;
    this.initPassword = true;
    this.regId = managerAccountRegReq.getRegManagerId();
  }

  public void setModifyInfo(ManagerAccountModReq managerAccountModReq) {
    this.memo = managerAccountModReq.getMemo();
    this.state = managerAccountModReq.getStateCode();
    this.roleSeq = managerAccountModReq.getRoleSeq();
    this.modDate = LocalDateUtils.krNow();
    this.modId = managerAccountModReq.getModId();
    this.phoneNumber = managerAccountModReq.getPhoneNumber();
    this.name = managerAccountModReq.getName();
  }

  public void settingByMyAccountModReq(ManagerMyAccountModReq managerMyAccountModReq) {
    this.name = managerMyAccountModReq.getName();
    this.phoneNumber = managerMyAccountModReq.getPhoneNumber();
    this.modId = managerMyAccountModReq.getModId();
    this.modDate = managerMyAccountModReq.getModDate();
  }

  public void settingByPasswordModReq(ManagerMyAccountModPasswordReq modPasswordReq) {
    this.password = modPasswordReq.getNewPassword();
    this.modId = modPasswordReq.getModId();
    this.modDate = modPasswordReq.getModDate();
    this.passwordErrorCount = 0;
    this.passwordModDate = LocalDateUtils.krNow();
    this.initPassword = false;
  }
}
