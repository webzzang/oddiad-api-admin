package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 고객 약관 동의
 */
@Data
@Entity
@ApiModel("고객 약관 동의")
@Table(name = "member_terms")
public class MemberTerms implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private MemberTermsPk memberTermsPk;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

}
