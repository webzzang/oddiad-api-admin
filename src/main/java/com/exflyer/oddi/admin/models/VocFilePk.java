package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * voc 파일
 */
@Data
@Embeddable
public class VocFilePk implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @ApiModelProperty("순번")
  @Column(name = "voc_seq", nullable = false)
  private Long vocSeq;

  /**
   * 파일 순번
   */
  @ApiModelProperty("파일 순번")
  @Column(name = "file_seq", nullable = false)
  private Long fileSeq;

}
