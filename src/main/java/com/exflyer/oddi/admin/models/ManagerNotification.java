package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 관리자 그룹 알림수신
 */
@Data
@Entity
@ApiModel("관리자 그룹 알림수신")
@Table(name = "manager_notification")
@IdClass(ManagerNotification.ID.class)
public class ManagerNotification implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("관리자 그룹순번")
  @Id
  @Column(name = "role_seq", nullable = false)
  private Long roleSeq;

  @ApiModelProperty("기기상태 알림코드")
  @Id
  @Column(name = "device_state_code", nullable = false)
  private String deviceStateCode;

  @ApiModelProperty("기기상태 알림수신여부")
  @Column(name = "device_push")
  private Boolean devicePush;

  @Data
  static class ID implements Serializable {
    private Long roleSeq;
    private String deviceStateCode;
  }

}
