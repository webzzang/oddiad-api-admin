package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 파트너 이미지
 */
@Data
@Entity
@ApiModel("파트너 이미지")
@Table(name = "partner_image")
@IdClass(PartnerImage.ID.class)
public class PartnerImage {

  private static final long serialVersionUID = 1L;

  /**
   * 파트너 순번
   */
  @ApiModelProperty("파트너 순번")
  @Column(name = "partner_seq", nullable = false)
  @Id
  private Long partnerSeq;

  /**
   * 파일 순번
   */
  @ApiModelProperty("파일 순번")
  @Column(name = "file_seq", nullable = false)
  @Id
  private Long fileSeq;

  /**
   * 노출 순서
   */
  @ApiModelProperty("노출 순서")
  @Column(name = "view_order")
  private Integer viewOrder;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;


  @Data
  static class ID implements Serializable {
    private Long partnerSeq;
    private Long fileSeq;
  }

}
