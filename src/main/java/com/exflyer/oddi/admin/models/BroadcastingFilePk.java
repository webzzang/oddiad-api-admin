package com.exflyer.oddi.admin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 방송 파일
 */
@Data
@Embeddable
public class BroadcastingFilePk implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 방송 순번
   */
  @ApiModelProperty("방송 순번")
  @Column(name = "broadcasting_seq", nullable = false)
  private Long broadcastingSeq;

  /**
   * 파일 순번
   */
  @ApiModelProperty("파일 순번")
  @Column(name = "adv_file_seq")
  private Long advFileSeq;

}
