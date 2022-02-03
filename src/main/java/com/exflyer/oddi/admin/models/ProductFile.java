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
 * 묶음상품 파일
 */
@Data
@Entity
@ApiModel("묶음상품 파일")
@Table(name = "product_file")
@IdClass(ProductFile.ID.class)
public class ProductFile {

  private static final long serialVersionUID = 1L;

  /**
   * 상품 순번
   */
  @Id
  @ApiModelProperty("상품 순번")
  @Column(name = "product_seq", nullable = false)
  private Long productSeq;

  /**
  * 파일 순번
  */
  @Id
  @Column(name = "file_seq", nullable = false)
  @ApiModelProperty("파일 순번")
  private Long fileSeq;

  /**
   * 등록 날짜
   */
  @ApiModelProperty("등록 날짜")
  @Column(name = "reg_date", nullable = false)
  private LocalDateTime regDate;

  /**
   * 등록 ID
   */
  @Column(name = "reg_id")
  @ApiModelProperty("등록 ID")
  private String regId;

  /**
   * 파일 type
   */
  @Column(name = "type")
  @ApiModelProperty("파일 type")
  private String type;

  @Data
  static class ID implements Serializable {
      private Long productSeq;
      private Long fileSeq;
  }

}
