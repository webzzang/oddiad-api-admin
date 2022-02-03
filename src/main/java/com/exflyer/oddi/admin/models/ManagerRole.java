package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 관리자 역활
 */
@Data
@Entity
@ApiModel("관리자 역활")
@Table(name = "manager_role")
public class ManagerRole implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private ManagerRolePk managerRolePk;

  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

}
