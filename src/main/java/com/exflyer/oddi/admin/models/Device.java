package com.exflyer.oddi.admin.models;

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
 * 장비
 */
@Data
@Entity
@ApiModel("장비")
@Table(name = "device")
public class Device implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @ApiModelProperty("id")
  @Column(name = "id", nullable = false)
  private String id;

  /**
   * 이름
   */
  @ApiModelProperty("이름")
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq")
  private Long partnerSeq;

  /**
   * 종류(크롬, TCL)
   */
  @ApiModelProperty("종류(크롬, TCL)")
  @Column(name = "type", nullable = false)
  private String type;

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

}
