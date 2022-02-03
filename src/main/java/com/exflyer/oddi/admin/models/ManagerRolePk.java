package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 관리자 역활
 */
@Data
@Embeddable
public class ManagerRolePk implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 관리자 id
   */
  @ApiModelProperty("관리자 id")
  @Column(name = "manager_id", nullable = false)
  private String managerId;

  /**
   * 역활 순번
   */
  @ApiModelProperty("역활 순번")
  @Column(name = "role_seq", nullable = false)
  private Long roleSeq;

}
