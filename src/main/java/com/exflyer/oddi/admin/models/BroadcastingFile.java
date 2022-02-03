package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * 방송 파일
 */
@Data
@Entity
@ApiModel("방송 파일")
@Table(name = "broadcasting_file")
public class BroadcastingFile implements Serializable {

  private static final long serialVersionUID = 1L;

  /*@EmbeddedId
  private BroadcastingFilePk broadcastingFilePk;*/

  /**
   * 순번
   */
  @Id
  @ApiModelProperty("순번")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seq", nullable = false)
  private Long seq;

  /**
   * 방송 순번
   */
  @ApiModelProperty("방송 순번")
  @Column(name = "broadcasting_seq", nullable = false)
  private Long broadcastingSeq;

  /**
   * 경로
   */
  @ApiModelProperty("경로")
  @Column(name = "path", nullable = false)
  private String path;

  /**
   * 생성 날짜
   */
  @ApiModelProperty("생성 날짜")
  @Column(name = "reg_date")
  private LocalDateTime regDate;

}
