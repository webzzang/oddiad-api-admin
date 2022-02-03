package com.exflyer.oddi.admin.models;

import com.exflyer.oddi.admin.api.manager.role.dto.RoleAddReq;
import com.exflyer.oddi.admin.api.manager.role.dto.RoleModReq;
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
 * 역활
 */
@Data
@Entity
@ApiModel("역활")
@Table(name = "role")
@NoArgsConstructor
public class Role implements Serializable {

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
   * 이름
   */
  @ApiModelProperty("이름")
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * 사용 여부
   */
  @ApiModelProperty("사용 여부")
  @Column(name = "usable", nullable = false)
  private boolean usable;

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

  /**
   * 메모
   */
  @Column(name = "memo")
  @ApiModelProperty("메모")
  private String memo;

  public Role(RoleAddReq roleAddReq) {
    this.name = roleAddReq.getName();
    this.usable = roleAddReq.getUsable();
    this.regDate = LocalDateUtils.krNow();
    this.regId = roleAddReq.getRegId();
    this.memo = roleAddReq.getMemo();
  }

  public void setInfoByModReq(RoleModReq modReq) {
    this.name = modReq.getName();
    this.usable = modReq.getUsable();
    this.modDate = LocalDateUtils.krNow();
    this.modId = modReq.getModId();
    this.memo = modReq.getMemo();
    this.seq = modReq.getRoleSeq();
  }
}
